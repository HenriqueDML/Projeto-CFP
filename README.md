# AppCFP - Sistema de Cadastro de Palestras

Este projeto é uma aplicação web construída com **Quarkus**, **Vaadin**, **MariaDB**, e utiliza **GraalVM** com suporte a **Java 21**. A aplicação realiza o cadastro e gerenciamento de palestras para eventos.

## 🛠️ Tecnologias Utilizadas

- **Java 21**
- **Quarkus 3.19.4**
- **Vaadin Flow**
- **Hibernate ORM + Panache**
- **MariaDB (via Testcontainers e DevServices)**
- **Docker (para ambiente de banco de dados)**
- **Bean Validation (JSR 380)**
- **RESTEasy Reactive**
- **Quarkus Security (OAuth2/OpenID para login externo)**
- **Testes com JUnit 5 e RestAssured**

## 📦 Estrutura do Projeto

```
src/
├── main/
│   ├── java/org/acme/
│   │   ├── controller/       # Controladores REST
│   │   ├── entity/           # Entidades JPA
│   │   ├── repository/       # Repositórios Panache
│   │   └── ui/               # Telas Vaadin
│   └── resources/            # application.properties, arquivos estáticos
├── test/java/org/acme/       # Testes unitários e de integração
```

## 🚀 Como Rodar

### Requisitos

- Java 21 + GraalVM
- Docker
- Maven 3.9+
- IDE recomendada: [InleJ](https://inlej.dev) ou IntelliJ IDEA

### Subindo com Dev Services

```bash
./mvnw quarkus:dev
```

> O banco de dados MariaDB será iniciado automaticamente com Testcontainers.

## ✅ Executar Testes

```bash
./mvnw test
```

## 🌐 Funcionalidades

- Cadastro de palestras (título, autor, e-mail, resumo)
- Filtros por título, autor e e-mail
- Validação de campos
- Interface Vaadin moderna e reativa
- Integração futura com login OAuth2 (ex: Google, GitHub)

## 📂 Banco de Dados

O banco é iniciado em container (via Testcontainers) com suporte para ambiente de testes e desenvolvimento automático (Dev Services).

## 👥 Autores

- Projeto desenvolvido por Henrique com apoio de assistente virtual.
