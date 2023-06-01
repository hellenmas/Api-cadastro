### Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas:
[Git](https://git-scm.com), [Intellij](https://www.jetbrains.com/pt-br/idea/). 

### 🎲 Rodando o Back End 

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
$ ./scripts/create-dynamodb-table.sh

### 🎲 Inserindo um cadastro via Curl

- `curl --location --request GET 'http://localhost:8080/cadastro/5e882139-5d09-4b68-86e1-864d504f0910' \
--header 'Content-Type: application/json' \
--data '{
    "title": "Livro",
    "content": "historia de fantasia",
    "userId": "123456"
}'`
