package de.kobe.fizz_buzz

import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.LocalDateTime

@Configuration
class FizzBuzzConfiguration {

    @Bean
    fun databaseInitializer(fizzBuzzRepository: FizzBuzzRepository) =
        ApplicationRunner {
            val dummyResult = FizzBuzzResponse.Success(
                timestamp = 4711,
                dateTimeBerlin = LocalDateTime.now().toString(),
                inputValue = 4711,
                outputValue = "4711"
            )
            fizzBuzzRepository.save(dummyResult)
        }

}