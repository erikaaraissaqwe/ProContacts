# Projeto ProContacts

Este projeto é uma aplicação de controle de cadastro de profissionais e contatos, desenvolvida em Java com o framework Spring Boot.

## Visão Geral

O objetivo deste projeto é fornecer uma API REST para gerenciar informações sobre profissionais e seus contatos associados. Ele oferece endpoints para realizar operações CRUD (Create, Read, Update, Delete) em entidades de contato e profissional.

## Tecnologias Utilizadas

- Java 17
- Spring Boot
- PostgreSQL
- Docker
- Postman (para testes de API)
- Swagger (para documentação da API)

## Estrutura do Projeto

O projeto é organizado em diferentes pacotes, incluindo modelos, serviços e controladores.

### Pacote `com.silvaerika.procontacts.model`

Este pacote contém as classes de modelo que representam as entidades do sistema.

### Pacote `com.silvaerika.procontacts.service`

Este pacote contém as interfaces e implementações dos serviços relacionados às entidades do sistema.

### Pacote `com.silvaerika.procontacts.controller`

Este pacote contém os controladores responsáveis por definir os endpoints da API e manipular as requisições HTTP.

## Endpoints da API

### Contatos

- **GET /api/v1/contatos/all**
    - Retorna todos os contatos cadastrados.
- **GET /api/v1/contatos**
    - Retorna contatos com base nos parâmetros especificados.
- **GET /api/v1/contatos/{id}**
    - Retorna um contato específico com o ID fornecido.
- **POST /api/v1/contatos**
    - Cria um novo contato com os dados fornecidos.
- **PUT /api/v1/contatos/{id}**
    - Atualiza um contato existente com os dados fornecidos.
- **DELETE /api/v1/contatos/{id}**
    - Exclui um contato com o ID fornecido.

### Contatos Profissionais

- **POST /api/v1/profissionalContatos**
    - Salva um profissional juntamente com seus contatos associados.

### Profissionais

- **GET /api/v1/profissionais/all**
    - Retorna todos os profissionais cadastrados.
- **GET /api/v1/profissionais**
    - Retorna profissionais com base nos parâmetros especificados.
- **GET /api/v1/profissionais/{id}**
    - Retorna um profissional específico com o ID fornecido.
- **POST /api/v1/profissionais**
    - Cria um novo profissional com os dados fornecidos.
- **PUT /api/v1/profissionais/{id}**
    - Atualiza um profissional existente com os dados fornecidos.
- **DELETE /api/v1/profissionais/{id}**
    - Remove logicamente um profissional com o ID fornecido.

## Configuração do Ambiente

### Docker e PostgreSQL

1. **Instale o Docker:**

   Verifique se o Docker está instalado em seu sistema. Você pode baixá-lo em [Docker's official website](https://www.docker.com/get-started).

2. **Crie o arquivo `docker-compose.yml`:**

   Crie um arquivo chamado `docker-compose.yml` e adicione o seguinte conteúdo:

   ```yaml
   version: '3.8'
   services:
     db:
       image: postgres
       environment:
         POSTGRES_DB: procontacts_db
         POSTGRES_USER: procontacts
         POSTGRES_PASSWORD: procontacts
       volumes:
         - postgres_data:/var/lib/postgresql/data
       ports:
         - "5432:5432"
     
     pgadmin:
       image: dpage/pgadmin4
       environment:
         PGADMIN_DEFAULT_EMAIL: admin@gmail.com
         PGADMIN_DEFAULT_PASSWORD: admin
       ports:
         - "5050:80"
       depends_on:
         - db
     
   volumes:
     postgres_data:
   ```

3. **Inicie o banco de dados:**

   Abra um terminal onde o arquivo `docker-compose.yml` está localizado e execute o seguinte comando:

   ```
   docker-compose up -d
   ```

   Isso iniciará o contêiner do PostgreSQL em segundo plano.

### Executando o Projeto

1. Clone o repositório para o seu ambiente de desenvolvimento.
2. Abra o projeto em sua IDE.
3. Certifique-se de ter o JDK 17 configurado corretamente.
4. Execute a aplicação a partir do arquivo principal (`ProcontactsApplication`).
5. Use o Postman para testar os endpoints da API. A coleção do Postman está disponível na pasta `postman`.

## Documentação da API

A documentação da API pode ser acessada em [http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/).

Seguindo estas etapas, você será capaz de configurar, executar e testar o projeto ProContacts em seu ambiente de desenvolvimento.