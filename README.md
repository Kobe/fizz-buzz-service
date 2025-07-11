[![verify_main_branch](https://github.com/Kobe/fizz-buzz-service/actions/workflows/verify.yml/badge.svg?branch=main)](https://github.com/Kobe/fizz-buzz-service/actions/workflows/verify.yml)

# Fizz Buzz REST API

## yet another coding challenge

This a coding challenge to get familiar with Spring Boot and its concepts.
Therefore, I've chosen to implement the fizz buzz group word game.

## Requirements

- endpoint to get the fizz buzz result for given (numeric) input value
- endpoint to get all existing fizz buzz results

## Prerequisites

- JDK 21
- Maven 3.9

## tech stack

- Spring Boot
- Spring Data
- Spring Doc / Swagger
- H2 in memory database

## Development

### clone repository

```bash
git clone https://github.com/Kobe/fizz-buzz-service.git
cd fizz-buzz-service
```

### run application

```bash
# http://localhost:8080
./mvnw spring-boot:run
```

### test application

```bash
./mvnw clean verify
```

### query database

```sql
# http://localhost:8080/fizz-buzz/db
# JDBC URL "jdbc:h2:mem:fizzbuzz"
# user "fizz"
# password "buzz"
SELECT * FROM FIZZ_BUZZ_RESULT
```

## License

fizz-buzz-service is licensed under the [GPLv3+.](LICENSE)

[![GPLv3](https://img.shields.io/badge/licence-GPLv3-brightgreen.svg)](http://www.gnu.org/licenses/gpl-3.0.html)
