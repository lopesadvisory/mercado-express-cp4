# Mercado Express - API

Checkpoint 4 (Parte 1 - API e Deploy) da disciplina de TDS, FIAP. Professor Dr. Marcel Stefan Wagner.

API REST para um mercado express (o exemplo que usamos aqui foi produtos de limpeza e mercearia), com CRUD completo e persistência no Oracle da FIAP.

## Integrantes

- Nicolas Monteiro Ramiro - RM 562380
- Marcus Vinicius Vila Nova da Silva - RM 558771
- Hebert Lopes dos Santos - RM 563192

## Tecnologias usadas

- Java 17
- Spring Boot 3.3.4 (Web, Data JPA, HATEOAS, Validation)
- Maven
- Lombok
- Oracle Database (SQL Developer / ORACLE_FIAP)
- Tomcat embutido, rodando na porta 8082

IDE utilizada: **IntelliJ IDEA**.

## Estrutura da tabela no banco

A aplicação usa a tabela `TDS_TB_MERCADO` no Oracle, com o seguinte formato:

| Coluna  | Tipo          |
|---------|---------------|
| ID      | NUMBER        |
| NOME    | VARCHAR2(100) |
| TIPO    | VARCHAR2(50)  |
| SETOR   | VARCHAR2(50)  |
| TAMANHO | VARCHAR2(20)  |
| PRECO   | NUMBER(10,2)  |

A tabela e a sequence usada para o ID (`TDS_SEQ_MERCADO`) são criadas automaticamente pelo Hibernate na primeira vez que a aplicação sobe (`spring.jpa.hibernate.ddl-auto=update`), então não é necessário criar nada manualmente no SQL Developer.

## Configuração de acesso ao banco

Por segurança, o usuário e a senha do Oracle não estão no código - eles são lidos de variáveis de ambiente:

- `DB_USER` - usuário do Oracle FIAP (RM)
- `DB_PASSWORD` - senha do Oracle FIAP

No IntelliJ, isso é configurado em **Run > Edit Configurations > Environment variables**, adicionando as duas variáveis com os valores de acesso ao ORACLE_FIAP.

## Como rodar o projeto localmente

1. Configurar as variáveis de ambiente `DB_USER` e `DB_PASSWORD` (acesso ao ORACLE_FIAP).
2. Rodar a classe `MercadoExpressApplication`.
3. A API sobe em `http://localhost:8082`.

## Endpoints

Base: `/mercado`

### GET /mercado

Lista todos os produtos cadastrados.

```json
{
  "_embedded": {
    "produtoList": [
      {
        "id": 1,
        "nome": "Detergente Neutro",
        "tipo": "Limpeza",
        "setor": "Higiene",
        "tamanho": "500ml",
        "preco": 3.49,
        "_links": {
          "self": { "href": "http://localhost:8082/mercado/1" },
          "mercado": { "href": "http://localhost:8082/mercado" },
          "atualizar": { "href": "http://localhost:8082/mercado/1" },
          "atualizar-parcial": { "href": "http://localhost:8082/mercado/1" },
          "deletar": { "href": "http://localhost:8082/mercado/1" }
        }
      }
    ]
  },
  "_links": {
    "self": { "href": "http://localhost:8082/mercado" }
  }
}
```

### GET /mercado/{id}

Busca um produto específico pelo ID.

```json
{
  "id": 1,
  "nome": "Detergente Neutro",
  "tipo": "Limpeza",
  "setor": "Higiene",
  "tamanho": "500ml",
  "preco": 3.49,
  "_links": {
    "self": { "href": "http://localhost:8082/mercado/1" },
    "mercado": { "href": "http://localhost:8082/mercado" },
    "atualizar": { "href": "http://localhost:8082/mercado/1" },
    "atualizar-parcial": { "href": "http://localhost:8082/mercado/1" },
    "deletar": { "href": "http://localhost:8082/mercado/1" }
  }
}
```

Se o ID não existir, retorna `404` com uma mensagem de erro.

### POST /mercado

Cadastra um novo produto. Corpo da requisição:

```json
{
  "nome": "Detergente Neutro",
  "tipo": "Limpeza",
  "setor": "Higiene",
  "tamanho": "500ml",
  "preco": 3.49
}
```

Retorna `201 Created` com o produto já com o ID gerado e os links do HATEOAS.

### PUT /mercado/{id}

Atualiza todos os campos de um produto existente. Corpo igual ao do POST.

```json
{
  "nome": "Detergente Neutro 1L",
  "tipo": "Limpeza",
  "setor": "Higiene",
  "tamanho": "1L",
  "preco": 6.99
}
```

### PATCH /mercado/{id}

Atualiza só os campos enviados no corpo da requisição, sem precisar mandar o produto completo.

```json
{
  "preco": 5.99
}
```

### DELETE /mercado/{id}

Remove o produto do banco pelo ID. Retorna `204 No Content`.

## HATEOAS

Cada resposta da API traz, além dos dados do produto, os links relacionados àquele recurso (`self`, `mercado`, `atualizar`, `atualizar-parcial`, `deletar`), seguindo o nível de maturidade 3 de Richardson: o cliente não precisa saber de antemão a estrutura das URLs, ele navega pelos links retornados pela própria API.

## Testes via Postman/Insomnia

Todos os endpoints foram testados no Postman, usando `localhost:8082`.

- **GET /mercado** - listagem geral
  (inserir print)
- **GET /mercado/{id}** - busca por ID
  (inserir print)
- **POST /mercado** - cadastro de um novo produto
  (inserir print)
- **PUT /mercado/{id}** - atualização completa
  (inserir print)
- **PATCH /mercado/{id}** - atualização parcial
  (inserir print)
- **DELETE /mercado/{id}** - exclusão
  (inserir print)

## Deploy

Link da aplicação publicada: (preencher após o deploy)

Repositório GitHub: (preencher com o link deste repositório)
