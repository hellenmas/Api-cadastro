
<h4 align="center"> 
	🚧  Projeto 🚀 Em construção...  🚧
</h4>
### 🛠 Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas:
[Git](https://git-scm.com), [Intellij](https://www.jetbrains.com/pt-br/idea/), [Docker](https://www.docker.com/). 

### ⚙️ Rodando o Back End 

```bash
# Clone este repositório
$ git clone <https://github.com/hellenmas/Api-cadastro.git>

# Acesse a pasta do projeto no terminal
$ cd demo

# Hora de subir docker-compose
$ make docker-start

# Crie a tabela atraves desse comando
$ chmod +x ./scripts/create-dynamodb-table.sh

# Execute o script logo em seguida
$ ./scripts/create-dynamodb-table.sh'

```
### ⚙️ Inserindo um cadastro via Curl
```bash

curl --location --request GET 'http://localhost:8080/cadastro/5e882139-5d09-4b68-86e1-864d504f0910' \
--header 'Content-Type: application/json' \
--data '{
    "title": "Livro",
    "content": "historia de fantasia",
    "userId": "123456"
}'
```
### ⚙️ Verificando a tabela e os dados inseridos
```bash
aws dynamodb scan --endpoint-url http://localhost:4566 --table-name posts
```
### ⚙️ Link para acessar o Swagger
```bash
http://localhost:8080/swagger-ui/index.html
```