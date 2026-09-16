# Events API

A **Events API** é uma aplicação REST desenvolvida em Java para cadastro e consulta de eventos. O sistema permite organizar eventos presenciais ou remotos, consultar os próximos eventos com paginação, filtrar resultados por diferentes critérios e associar cupons de desconto aos eventos.

A aplicação foi estruturada para servir como backend de uma plataforma de divulgação e gerenciamento de eventos, com persistência dos dados em PostgreSQL.

---

## ✨ Funcionalidades

- [x] **Cadastro de eventos:** registre título, descrição, data, URL e indique se o evento é remoto ou presencial.
- [x] **Endereço de eventos presenciais:** armazene cidade e estado (UF) para eventos que não são remotos.
- [x] **Consulta de evento por identificador:** obtenha os detalhes de um evento e seus cupons válidos.
- [x] **Listagem de próximos eventos:** consulte eventos futuros com paginação.
- [x] **Filtros de eventos:** pesquise por título, cidade, UF e intervalo de datas.
- [x] **Cupons de desconto:** associe cupons a eventos, informando código, desconto e data de validade.
- [x] **Migrations do banco de dados:** crie e atualize a estrutura do PostgreSQL com Flyway.

> O contrato de criação de eventos já aceita uma imagem por `MultipartFile`. O armazenamento do arquivo ainda está preparado para implementação e, no momento, não realiza o upload.

---

## 🛠️ Tecnologias Utilizadas

- **[Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html):** linguagem e plataforma de execução.
- **[Spring Boot](https://spring.io/projects/spring-boot) 4.1.1:** configuração e execução da aplicação.
- **[Spring Web MVC](https://docs.spring.io/spring-framework/reference/web/webmvc.html):** criação dos endpoints REST.
- **[Spring Data JPA](https://spring.io/projects/spring-data-jpa):** persistência e acesso aos dados.
- **[PostgreSQL](https://www.postgresql.org/):** banco de dados relacional.
- **[Flyway](https://documentation.red-gate.com/flyway):** versionamento e execução das migrations do banco.
- **[Maven](https://maven.apache.org/):** gerenciamento de dependências e automação do build.
- **[Lombok](https://projectlombok.org/):** redução de código repetitivo nas classes Java.
- **[Docker Compose](https://docs.docker.com/compose/):** execução local do PostgreSQL.

---

## 📌 Principais Endpoints

### Eventos

| Método | Endpoint               | Descrição                                               |
| ------ | ---------------------- | ------------------------------------------------------- |
| `POST` | `/api/event`           | Cadastra um evento.                                     |
| `GET`  | `/api/event`           | Lista os próximos eventos com paginação.                |
| `GET`  | `/api/event/{eventId}` | Retorna os detalhes de um evento e seus cupons válidos. |
| `GET`  | `/api/event/filter`    | Filtra eventos por título, cidade, UF e datas.          |

Parâmetros de paginação:

```text
page=0&size=10
```

Parâmetros opcionais de filtro:

```text
title, city, uf, startDate, endDate
```

As datas dos filtros devem usar o formato ISO, por exemplo: `2026-09-16`.

### Cupons

| Método | Endpoint                      | Descrição                                |
| ------ | ----------------------------- | ---------------------------------------- |
| `POST` | `/api/coupon/event/{eventId}` | Adiciona um cupom a um evento existente. |

---

## ▶️ Como Executar o Projeto

### Pré-requisitos

- Java 17 ou superior;
- Docker e Docker Compose;
- Git.

### 1. Clone o repositório

```bash
git clone https://github.com/Maycon40/events-api.git
cd events-api
```

### 2. Inicie o PostgreSQL

O projeto possui um `docker-compose.yaml` que cria um banco PostgreSQL com os parâmetros usados pela aplicação:

```bash
docker compose up -d
```

A configuração padrão é:

- Banco: `eventstec`
- Usuário: `postgres`
- Senha: `123456`
- Porta: `5432`

> Em ambientes reais, altere essas credenciais e atualize o arquivo `src/main/resources/application.properties` ou externalize as configurações por variáveis de ambiente.

### 3. Execute a aplicação

Linux/macOS:

```bash
./mvnw spring-boot:run
```

Windows:

```bash
mvnw.cmd spring-boot:run
```

Na primeira execução, o Flyway cria as tabelas de eventos, cupons e endereços automaticamente.

A API ficará disponível em:

```text
http://localhost:8080
```

### 4. Execute os testes

Linux/macOS:

```bash
./mvnw test
```

Windows:

```bash
mvnw.cmd test
```

### 5. Pare o banco de dados

```bash
docker compose down
```

---

## 📄 Exemplo de requisição

Criação de um evento remoto:

```http
POST http://localhost:8080/api/event
Content-Type: application/json

{
  "title": "Workshop de Java",
  "description": "Introdução ao desenvolvimento de APIs com Java",
  "date": 1792195200000,
  "remote": true,
  "eventUrl": "https://exemplo.com/workshop-java"
}
```

Listagem paginada:

```http
GET http://localhost:8080/api/event?page=0&size=10
```

Filtragem por localidade:

```http
GET http://localhost:8080/api/event/filter?city=São Paulo&uf=SP&page=0&size=10
```

---

## 📁 Estrutura principal

```text
src/main/java/com/eventstec/api/
├── controller/     # Endpoints REST
├── domain/         # Entidades e DTOs
├── repositories/   # Repositórios Spring Data
└── service/        # Regras de negócio

src/main/resources/db/migration/
└── V*.sql           # Migrations do banco de dados
```
