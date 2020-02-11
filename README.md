# Fizz Buzz - yet another coding challenge

Erstelle ein WebApp, die an das bekannte “Fizz Buzz” angelehnt ist - siehe ttps://en.wikipedia.org/wiki/Fizz_buzz

Erstelle eine Eingabemöglichkeit für numerische Werte, deren Ergebnis in einer sortierten Liste (aufsteigend nach Eingabewert) ausgegeben wird.

Pro Berechnung, die via REST-Endpunkt im Back-End erfolgt, wird die Ergebnisliste unter Berücksichtigung der Sortierung entsprechend erweitert.

## Technische Anforderungen

Das Back-End ist eine Spring-Boot-Anwendung, die in Kotlin oder Java implementiert wurde.

Das Front-End ist, wenn möglich, mit ReactJS umgesetzt.

Für jede Berechnung soll zusätzlich der aktuelle timestamp über die API http://worldclockapi.com/api/json/cet/now geholt und mit ausgegeben werden.

Alle bisherigen Ergebnisse werden bei einem Wechsel des Browsers initial angezeigt.

BONUS: Die WebApp ist auf allen Device-Typen gut zu bedienen. Neben den gängigen Desktop- und mobilen Browsern ist der Internet Explorer 11 zu unterstützen.

## prerequisites

- Java JDK 8
- Kotlin 1.3.x

## run application

```mvn spring-boot:run```

## test application

```mvn verify```

## License

fizz-buzz-service is licensed under the [GPLv3+.](LICENSE)
