package dev.leandro.viveiros.generator.service;

import dev.leandro.viveiros.generator.model.User;
import lombok.AllArgsConstructor;
import lombok.val;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * Serviço com utilitários para webCrawler
 */
@Service
@AllArgsConstructor
public class WebCrawlerService {


    private final WebDriver driver;

    private final UserService userService;

    /**
     *ao iniciar a aplicação acessar os geradores e manipular os dados
     *
     */
    @PostConstruct
    public void init() {
        if(userService.getAllUsers().isEmpty()) {
            // acessar o gerador de nick setar o form como aleatório com limite de 8.
            driver.get("https://www.4devs.com.br/gerador_de_nicks");
            val method = new Select(driver.findElement(By.name("method")));
            method.selectByVisibleText("Aleatório");
            val limit = new Select(driver.findElement(By.name("limit")));
            limit.selectByVisibleText("8");

            // setar a quantidade como 50 e clcar em gerar.
            driver.findElement(By.xpath("//input[@id='quantity']")).clear();
            driver.findElement(By.xpath("//input[@id='quantity']")).sendKeys("50");
            driver.findElement(By.xpath("//input[@id='bt_gerar_nick']")).click();

            // pegar o resultado gerado e treansforma em user com o nome.
            val users = driver.findElements(By.className("generated-nick")).stream()
                    .map(this::maptoUser)
                    .collect(Collectors.toList());

            // acessar o gerador de cpf, pegar o cpf setar no user e salvar no banco
            driver.get("https://www.4devs.com.br/gerador_de_cpf");
            users.forEach(this::generateCPF);

            // fechar o drive
            driver.close();
        }
    }

    /**
     *gera o cpf de usuario e salva no banco
     *
     * @param user O usuario a ter o cpf gerado
     */
    private void generateCPF(User user) {
        val continua = new AtomicBoolean(true);

        driver.findElement(By.xpath("//input[@id='bt_gerar_cpf']")).click();

        do{
            val cpf = driver.findElement(By.id("texto_cpf")).getAttribute("innerText");
            if  (cpf.length() == 14)  {
                user.setCpf(cpf);
                driver.navigate().refresh();
                userService.createUser(user.getName(), user.getCpf());
                continua.set(false);
            }
        }while(continua.get());
    }

    /**
     * transforma WebElement em User
     *
     * @param webElement O webelement a ser transformado
     * @return Um usuario com o nome extraido do WebElement
     */
    private User maptoUser(WebElement webElement) {
        val user = new User();
        user.setName(webElement.getText());
        return user;
    }
}
