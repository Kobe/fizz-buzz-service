# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Spring Boot REST service (Kotlin, JDK 21, Spring Boot 4) that calculates FizzBuzz results for a given number, timestamps them with the current Berlin time from an external World Clock API and persists them in an H2 in-memory database. It is a learning project for Spring Boot concepts.

## Commands

```bash
./mvnw spring-boot:run                      # Start on http://localhost:8080 (redirects to Swagger UI)
./mvnw clean verify                         # Unit tests (surefire) + integration tests *IT (failsafe)
./mvnw test -Dtest=FizzBuzzServiceTest      # Single unit test class
./mvnw verify -Dit.test=FizzBuzzControllerIT # Single integration test (unit tests still run)
```

CI (`.github/workflows/verify*.yml`) runs `mvn verify` on pushes to `main` and on pull requests. Dependabot bumps Maven dependencies weekly.

## Architecture

All code lives in the single package `de.kobe.fizz_buzz` (`src/main/kotlin/de/kobe/fizz_buzz/`).

- `FizzBuzzController` – `GET /fizz-buzz/` (all stored results), `GET /fizz-buzz/{value}` (calculate + persist)
- `MainController` – redirects `/` to `/swagger-ui/index.html`
- `FizzBuzzService` – orchestrates: fetches Berlin time, calculates, saves; returns the sealed `FizzBuzzResponse` (`Success` / `Failure`)
- `FizzBuzzCalculationService` – pure FizzBuzz logic (checks 15 before 3 and 5)
- `FizzBuzzRepository` – Spring Data `CrudRepository`; the `FizzBuzzResult` JPA entity is defined in the same file
- `WorldClockClient` – WebClient call to `services.worldclockapi.url` (`/Europe/Berlin.json`, 1s timeout), maps failures to the sealed `WorldClockResult.Error` subtypes; the service currently calls `.block()` on it
- `GlobalExceptionHandler` – `@RestControllerAdvice` mapping exceptions to `ErrorResponse` (400 / 503 for `FizzBuzzException` / 500)

Configuration is in `src/main/resources/application.properties` (Swagger UI, H2 console at `/fizz-buzz/db`, datasource, World Clock URL).

## Testing Conventions

- Unit tests: `*Test.kt`; integration tests: `*IT.kt` (run by failsafe during `verify`)
- JUnit 5 + AssertJ, test names in backticks, given/when/then comments
- `WorldClockClientIT` stubs the external API with WireMock; never hit the real API in tests
- DB tests use `@DataJpaTest` / `TestEntityManager`

## Further Docs

- `docs/plan.md` / `docs/tasks.md` – improvement plan and task checklist (tick off tasks when done)
- `.junie/guidelines.md` – older, more verbose guidelines; partly generic (e.g. its `StringUtilsTest` example does not exist)
