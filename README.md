# Fizz Buzz REST api

## yet another coding challenge

fizz buzz calculation on a REST basis

[![GPLv3](https://img.shields.io/badge/licence-GPLv3-brightgreen.svg)](http://www.gnu.org/licenses/gpl-3.0.html)

fizz-buzz-service is licensed under the [GPLv3+.](LICENSE)

## Prerequisites

- Java JDK 11
- latest Kotlin version

## tech stack

- Spring Boot (incl. JPA, H2)
- Kotlin

## Demo

### by Swagger Api Documentation

There is a live demo: [https://fizz-buzz-service.herokuapp.com](https://fizz-buzz-service.herokuapp.com).
Please give Heroku a sec or two to start up.

## Development

### clone repository

```git clone https://github.com/Kobe/fizz-buzz-service.git```
```cd fizz-buzz-service```

### run application

```mvn spring-boot:run```

```
# http://localhost:8080/fizz-buzz/db (user=fizz, password=buzz)
SELECT * FROM FIZZ_BUZZ_RESULT
```

### test application

```mvn clean verify```

### query database (h2 inmemory)

```
# user "fizz"
# password "buzz"
http://localhost:8080/fizzbuzz/db/
```