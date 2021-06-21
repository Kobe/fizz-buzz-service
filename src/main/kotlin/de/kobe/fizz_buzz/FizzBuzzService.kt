package de.kobe.fizz_buzz

import org.springframework.stereotype.Service
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Service
class FizzBuzzService (
    private val worldClockClient: WorldClockClient,
    private val fizzBuzzRepository: FizzBuzzRepository
) {

    fun getFizzBuzzResults(): Iterable<FizzBuzzResponse.Success> {
        return fizzBuzzRepository.findAll()
    }

    fun calculateFizzBuzzResult(value: Int): FizzBuzzResponse {
        val currentBerlinTime = worldClockClient.getCurrentBerlinTime().block()

        if (currentBerlinTime == null || currentBerlinTime.datetime.isEmpty()) {
            return FizzBuzzResponse.Failure
        }

        val fizzBuzzResponse = FizzBuzzResponse.Success(
            timestamp = currentBerlinTime.unixtime,
            dateTimeBerlin = currentBerlinTime.datetime,
            inputValue = value,
            outputValue = calculate(value)
        )

        fizzBuzzRepository.save(fizzBuzzResponse)

        return fizzBuzzResponse

    }

    private fun calculate(value: Int): String {
        return when {
            value % 15 == 0 -> "Fizz Buzz"
            value % 3 == 0 -> "Fizz"
            value % 5 == 0 -> "Buzz"
            else -> "$value"
        }
    }
}

sealed class FizzBuzzResponse {

    object Failure: FizzBuzzResponse()

    @Entity
    data class Success (
        @Id @GeneratedValue
        val id: UUID = UUID.randomUUID(),
        val timestamp: Long,
        val dateTimeBerlin: String,
        val inputValue: Int,
        val outputValue: String
    ): FizzBuzzResponse()

}