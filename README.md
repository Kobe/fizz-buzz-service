[![verify_main_branch](https://github.com/Kobe/fizz-buzz-service/actions/workflows/verify.yml/badge.svg?branch=main)](https://github.com/Kobe/fizz-buzz-service/actions/workflows/verify.yml)

# Fizz Buzz REST API

## yet another coding challenge

This a coding challenge to get familiar with Spring Boot and its lifecycle.
Therefore, I've chosen to implement the fizz buzz group word game.

## Requirements

- endpoint to get the fizz buzz result for given (numeric) input value
- endpoint to get all existing fizz buzz results

## Prerequisites

- JDK 11
- recent Maven version
- recent Kotlin version

## tech stack

- Spring Boot 
- Spring Data 
- H2 in memory database
- Swagger

## Demo - Swagger API Documentation

There is a live demo: [https://fizz-buzz-service.herokuapp.com](https://fizz-buzz-service.herokuapp.com).
Please give Heroku a sec or two to start up.

## Development

### clone repository

```bash
git clone https://github.com/Kobe/fizz-buzz-service.git
cd fizz-buzz-service
```

### run application

```bash
mvn spring-boot:run # http://localhost:8080
```

#### with Heroku CLI

```bash
heroku local # http://localhost:5000
```

### test application

```bash
mvn clean verify
```

### query database

```sql
# http://localhost:8080/fizzbuzz/db
# user "fizz"
# password "buzz"
SELECT * FROM FIZZ_BUZZ_RESULT
```

## License

fizz-buzz-service is licensed under the [GPLv3+.](LICENSE)

[![GPLv3](https://img.shields.io/badge/licence-GPLv3-brightgreen.svg)](http://www.gnu.org/licenses/gpl-3.0.html)
