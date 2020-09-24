## Aplicativo de gerador de nicks

## Usando API rest

Acesse o site localmente: http://localhost:8080/user/todos
lá terá um JSON com todos os usuários gerados após inicialização!

## Tecnológias utilizadas

- Selenium 3.141.59
- SpringBoot 2.1.4
- SpringFramework 5.1.6
- Flyway 5.0.0
- Hibernate 5.2.17
- PostgreSQL 42.2.5
- JDA 3.8.3_462
- Lombok 1.18.8

## Sobre

O aplicativo utiliza SpringBoot e o SpringFramework como framework e Hibernate como persistência junto com o PostgreSQL
 e também selenium com chromedriver para fazer o crawler

## Editar código localmente

- Para editar o código em seu computador utilize o plugin do lombok disponível no marketplace da IDE escolhida

## Execução local
- baixe o chrome driver com a mesma versão do seu navegador e coloque o caminho na configuração chrome-drive dentro da application.properties
- Para gerar uma jar executável utilize o comando do maven `$ mvn clean install`
- Para executar a jar gerada utilize no cmd  `$ java -jar gerador-nick.jar`


## Requisitos para execução

- Configurar um banco de dados utilizando PostgreSQL
- Configure as variáveis de ambiemte `database_url, database_user, database_url`
