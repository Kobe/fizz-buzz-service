# Fizz Buzz web app - yet another coding challenge

[![GPLv3](https://img.shields.io/badge/licence-GPLv3-brightgreen.svg)](http://www.gnu.org/licenses/gpl-3.0.html)
![Heroku](https://heroku-badge.herokuapp.com/?app=fizz-buzz-service)

## Technische Anforderungen

Das Back-End ist eine Spring-Boot-Anwendung, die in Kotlin oder Java implementiert wurde.

Für jede Berechnung soll zusätzlich der aktuelle timestamp über die API [http://worldclockapi.com/](http://worldclockapi.com/) geholt und ausgegeben werden.

Alle bisherigen Ergebnisse werden bei einem Wechsel des Browsers initial angezeigt.

## Prerequisites

- Java JDK 11
- latest Kotlin version (1.4.x)

## Get started

```
git clone https://github.com/Kobe/fizz-buzz-service.git
cd fizz-buzz-service
mvn spring-boot:run
```

## Demo

A live demo you wil found here: [https://fizz-buzz-service.herokuapp.com](https://fizz-buzz-service.herokuapp.com).
Please give heroku a sec or two (30) to start up.

## License

fizz-buzz-service is licensed under the [GPLv3+.](LICENSE)

## Development

### run application

```mvn spring-boot:run```

### test application

```mvn clean verify```
