web: java -Dserver.port=$PORT $JAVA_OPTS -jar target/fizz_buzz-0.0.1-SNAPSHOT.jar
release: ./mvnw -Dliquibase.changeLogFile=src/main/resources/liquibase-changelog.xml -Dliquibase.url=jdbc:h2:mem:fizzbuzz -Dliquibase.promptOnNonLocalDatabase=false liquibase:update
