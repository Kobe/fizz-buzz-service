# Fizz Buzz Service Development Guidelines

This document provides guidelines and instructions for developing and maintaining the Fizz Buzz Service project.

## Build/Configuration Instructions

### Prerequisites

- JDK 21
- Maven 3.9 (or use the included Maven wrapper)

### Building the Project

The project uses Maven for build management. The Maven wrapper (`mvnw`) is included in the project, so you don't need to install Maven separately.

```bash
# Build the project
./mvnw clean install

# Build without running tests
./mvnw clean install -DskipTests
```

### Running the Application

```bash
# Start the application on http://localhost:8080
./mvnw spring-boot:run
```

### Configuration

The application uses Spring Boot's configuration system. The main configuration file is `src/main/resources/application.properties`.

Key configuration properties:

- Database configuration (H2 in-memory database)
- External service URLs (e.g., World Clock API)

## Testing Information

### Test Structure

The project follows a standard test structure:

- Unit tests: Test individual components in isolation (e.g., `FizzBuzzCalculationServiceTest`)
- Integration tests: Test interactions between components (e.g., `FizzBuzzControllerIT`)

Tests are located in the `src/test/kotlin` directory, mirroring the package structure of the main code.

### Running Tests

```bash
# Run all tests
./mvnw clean verify

# Run a specific test class
./mvnw test -Dtest=StringUtilsTest

# Run a specific test method
./mvnw test -Dtest=StringUtilsTest#can_reverse_a_string
```

### Test Types

1. **Unit Tests**
   - Use JUnit 5 and AssertJ for assertions
   - Focus on testing a single component in isolation
   - Naming convention: `ClassNameTest.kt`

2. **Integration Tests**
   - Use Spring Boot's testing framework
   - Test interactions between components
   - May use MockMvc for testing controllers
   - May use WireMock for mocking external services
   - Naming convention: `ClassNameIT.kt`

### Creating a New Test

1. Create a new test class in the appropriate package in `src/test/kotlin`
2. Use JUnit 5 annotations (`@Test`, `@BeforeEach`, etc.)
3. Use AssertJ for assertions (`Assertions.assertThat()`)
4. Follow the existing test patterns:
   - Descriptive test method names using backticks (e.g., ``can reverse a string``)
   - Given-When-Then structure with comments

### Example Test

Here's an example of a simple unit test:

```kotlin
class StringUtilsTest {

    @Test
    fun `can reverse a string`() {
        // given
        val input = "hello"
        val expected = "olleh"
        
        // when
        val result = StringUtils.reverse(input)
        
        // then
        Assertions.assertThat(result).isEqualTo(expected)
    }
}
```

### Database Testing

The project uses an H2 in-memory database for testing. Integration tests that interact with the database should:

1. Use `@SpringBootTest` to load the application context
2. Clean up the database after each test using `@AfterEach`

Example:

```kotlin
@AfterEach
fun cleanUp() {
    dataSource.connection.use { connection ->
        connection.prepareStatement("DELETE FROM fizz_buzz_result").execute()
    }
}
```

### Mocking External Services

The project uses WireMock to mock external services in integration tests. Example:

```kotlin
@BeforeEach
fun setup() {
    wireMockServer = WireMockServer(WireMockConfiguration.options().port(8081))
    wireMockServer.start()

    wireMockServer.stubFor(
        WireMock.get(WireMock.urlEqualTo("/Europe/Berlin.json"))
            .willReturn(
                WireMock.aResponse()
                    .withStatus(200)
                    .withHeader("Content-Type", "application/json")
                    .withBody("""{"datetime":"2021-01-31T17:06:09.172975+01:00","unixtime":1605391751}""")
            )
    )
}

@AfterEach
fun tearDown() {
    wireMockServer.stop()
}
```

## Additional Development Information

### Project Structure

- `src/main/kotlin/de/kobe/fizz_buzz/`: Main application code
- `src/test/kotlin/de/kobe/fizz_buzz/`: Test code
- `src/main/resources/`: Configuration files

### Key Components

- `FizzBuzzApplication`: Main application entry point
- `FizzBuzzController`: REST controller for the FizzBuzz API
- `FizzBuzzService`: Service layer for business logic
- `FizzBuzzRepository`: Data access layer
- `FizzBuzzCalculationService`: Core calculation logic
- `WorldClockClient`: Client for the external World Clock API

### API Documentation

The project uses SpringDoc/Swagger for API documentation. When the application is running, you can access the API documentation at:

```
http://localhost:8080/swagger-ui.html
```

### Database Access

The application uses an H2 in-memory database. You can access the H2 console at:

```
http://localhost:8080/fizz-buzz/db
```

Connection details:
- JDBC URL: `jdbc:h2:mem:fizzbuzz`
- Username: `fizz`
- Password: `buzz`

### Code Style

The project follows Kotlin coding conventions:

- Use 4 spaces for indentation
- Use camelCase for variable and function names
- Use PascalCase for class names
- Use descriptive names for functions and variables
- Add KDoc comments for public APIs

### Dependency Management

The project uses Maven for dependency management. Key dependencies:

- Spring Boot (Web, Data JPA, Actuator)
- H2 Database
- Jackson for JSON processing
- Kotlin libraries (kotlin-reflect, reactor-kotlin-extensions)
- Testing libraries (JUnit 5, AssertJ, WireMock, Mockito)

### Error Handling

The application uses a sealed class approach for handling responses:

```kotlin
sealed class FizzBuzzResponse {
    object Failure: FizzBuzzResponse()
    data class Success(
        val id: UUID,
        val timestamp: Long,
        val dateTimeBerlin: String,
        val inputValue: Int,
        val outputValue: String
    ): FizzBuzzResponse()
}
```

This pattern allows for type-safe handling of different response types.

### Transactional Behavior

Service methods that modify data are annotated with `@Transactional` to ensure data consistency.
