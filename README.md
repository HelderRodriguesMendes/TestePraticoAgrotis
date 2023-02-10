# Teste Pratico Agrotis

Para a execução desse projeto é necessário criar uma imagem Docker a partir do arquivo Docker-compose que está na raiz do projeto, pois a base de dados do projeto está configurada em uma imagem docker.

Após a criação da imagem docker com a base de dados, é necessário alterar a configuração: 
spring.jpa.hibernate.ddl-auto=update, trocando o update pro create 
Essa configuração se encontra no arquivo application.properties no projeto, após a alteração execute a aplicação para que ela crie as tabelas na base de dados.
Após as tabelas serem criadas, desfaça a alteração no arquivo application.properties.

A aplicação contém o CRUD completo de 3 entidades e possui também testes unitários com jUnit e Mockito, as demais tecnologias utilizadas foram:
Java, Spring Boot, JPA, Hibernate, Maven, Docker, DTO, ModelMapper, Lombok e banco de dados MySQL
