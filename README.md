# AppCFP - Sistema de Cadastro de Palestras

Este projeto é uma aplicação web construída com **Quarkus**, **Vaadin**, **MariaDB**, e utiliza **GraalVM** com suporte a **Java 21**. A aplicação realiza o cadastro e gerenciamento de palestras para eventos.

## 🛠️ Projeto Rodando
![cfpimg](https://github.com/user-attachments/assets/39ec03e8-4053-4479-8532-7af2563e7b04)

## 🛠️ Pequeno Video sobre o Projeto
Link: https://youtu.be/n6O6-384jHc

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

## 🚀 Como Rodar

### Pré-requisitos

- **Java 21 com GraalVM**
- **Docker** instalado e em execução
- **Maven 3.9+**
- **IDE** recomendada: InleJ ou IntelliJ IDEA

### Passos

1. **Clone o projeto**

```bash
git clone <URL-do-repositório>
cd appcfp
```

2. **Execute em modo desenvolvimento**

```bash
./mvnw quarkus:dev
```

> O banco de dados MariaDB será iniciado automaticamente em um container com Testcontainers (Dev Services do Quarkus).

3. **Acesse no navegador**

```
http://localhost:8080
```

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

