# Fizz Buzz web app - yet another coding challenge

[![GPLv3](https://img.shields.io/badge/licence-GPLv3-brightgreen.svg)](http://www.gnu.org/licenses/gpl-3.0.html)
![Heroku](https://heroku-badge.herokuapp.com/?app=fizz-buzz-service&root=fizz-buzz/15)

Erstelle ein web app, die an das bekannte “Fizz Buzz” angelehnt ist - siehe [https://en.wikipedia.org/wiki/Fizz_buzz](https://en.wikipedia.org/wiki/Fizz_buzz)

Erstelle eine Eingabemöglichkeit für numerische Werte, deren Ergebnis in einer sortierten Liste (aufsteigend nach Eingabewert) ausgegeben wird.

Pro Berechnung, die via REST-Endpunkt im Back-End erfolgt, wird die Ergebnisliste unter Berücksichtigung der Sortierung entsprechend erweitert.

## Technische Anforderungen

Das Back-End ist eine Spring-Boot-Anwendung, die in Kotlin oder Java implementiert wurde.

Das Front-End ist, wenn möglich, mit ReactJS umgesetzt.

Für jede Berechnung soll zusätzlich der aktuelle timestamp über die API [http://worldclockapi.com/api/json/cet/now](http://worldclockapi.com/api/json/cet/now) geholt und ausgegeben werden.

Alle bisherigen Ergebnisse werden bei einem Wechsel des Browsers initial angezeigt.

BONUS: Die web app ist auf allen Device-Typen gut zu bedienen. Neben den gängigen Desktop- und mobilen Browsern ist der Internet Explorer 11 zu unterstützen.

## Prerequisites

- Java JDK 11
- latest Kotlin version (1.3.x)

## Get started

Fetch a release or build it from source:

```
git clone https://github.com/Kobe/fizz-buzz-service.git
cd fizz-buzz-service
mvn spring-boot:run
```

## Demo

A live demo can be found here: [https://fizz-buzz-service.herokuapp.com](https://fizz-buzz-service.herokuapp.com).
Please give heroku a sec or two (30) to start up.

## License

fizz-buzz-service is licensed under the [GPLv3+.](LICENSE)

## Development

### run application

```mvn spring-boot:run```

### test application

```mvn clean verify```
