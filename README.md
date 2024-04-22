# Projeto de Controle de Cadastro de Profissionais e Contatos

Este projeto oferece uma API REST em Java Spring Boot para o controle de cadastro de profissionais e seus respectivos contatos.

## Endpoints

### Contatos

- **GET /contatos**

  Retorna uma lista de contatos.

  **Response:** Lista de contatos

- **GET /contatos/:id**

  Retorna todos os detalhes do contato com o ID especificado na URL.

  **Response:** Todos os detalhes do contato

- **POST /contatos**

  Insere os dados do contato no banco de dados conforme especificado no corpo da requisição.

  **Body:** Content-type: Json

  **Response:** Contato cadastrado com sucesso. ID: {ID}

- **PUT /contatos/:id**

  Atualiza os dados do contato com o ID especificado na URL com os dados fornecidos no corpo da requisição.

  **Body:** Content-type: Json

  **Response:** Contato alterado com sucesso

- **DELETE /contatos/:id**

  Remove o contato com o ID especificado na URL.

  **Response:** Contato excluído com sucesso

### Profissionais

- **GET /profissionais**

  Retorna uma lista de profissionais.

  **Response:** Lista de profissionais

- **GET /profissionais/:id**

  Retorna todos os detalhes do profissional com o ID especificado na URL.

  **Response:** Todos os detalhes do profissional

- **POST /profissionais**

  Insere os dados do profissional no banco de dados conforme especificado no corpo da requisição.

  **Body:** Content-type: Json

  **Response:** Profissional cadastrado com sucesso. ID: {ID}

- **PUT /profissionais/:id**

  Atualiza os dados do profissional com o ID especificado na URL com os dados fornecidos no corpo da requisição.

  **Body:** Content-type: Json

  **Response:** Profissional alterado com sucesso

- **DELETE /profissionais/:id**

  Remove logicamente o profissional com o ID especificado na URL.

  **Response:** Profissional excluído com sucesso

## Configuração do Banco de Dados PostgreSQL com Docker

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

## Execução do Projeto no IntelliJ IDEA

1. **Clonando o repositório:**

   Clone o repositório do projeto para o seu ambiente de desenvolvimento.

2. **Abrindo o projeto no IntelliJ IDEA:**

   Abra o IntelliJ IDEA e clique em `File -> Open` para abrir o projeto.

3. **Configurando o ambiente:**

   Certifique-se de ter o JDK 17 configurado corretamente no IntelliJ IDEA.

4. **Executando a aplicação:**

   No IntelliJ IDEA, encontre o arquivo principal da aplicação (ProcontactsApplication) e execute-o.

5. **Testando a API:**

   Use o Postman para enviar requisições aos endpoints definidos e verificar o funcionamento da API. A coleção do postman está na pasta postman.

Seguindo essas etapas, você poderá executar e testar o projeto no IntelliJ IDEA.