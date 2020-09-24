package dev.leandro.viveiros.generator.configuration;

import lombok.val;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

/**
 * Configuração do selium usando chrome
 */

@Configuration
public class SeleniumSetup {




    // o local onde está o chromedriver versão 85
    @Value("${chrome-drive}")
    public String chromeDrivePath;


    /**
     * Gerar a bean do webdrive
     *
     * @return o WebDriver(chrome) já com chromedriver configurado
     */
    @Bean
    public WebDriver setupWebDrive() {
        val file = new File(chromeDrivePath);
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
        return new ChromeDriver();
    }
}
