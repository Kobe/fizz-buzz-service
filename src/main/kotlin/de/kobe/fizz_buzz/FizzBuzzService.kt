package de.kobe.fizz_buzz

import org.springframework.stereotype.Service
import java.util.*

@Service
class FizzBuzzService (
    private val worldClockClient: WorldClockClient
) {

    fun getFizzBuzzResult(value: Int): FizzBuzzResponse {
        val currentBerlinTime = worldClockClient.getCurrentBerlinTime().block()!!

        if (currentBerlinTime.datetime.isEmpty()) {
            return FizzBuzzResponse.Failure
        }

        return FizzBuzzResponse.Success(
            timestamp = currentBerlinTime.unixtime,
            dateTimeBerlin = currentBerlinTime.datetime,
            inputValue = value,
            outputValue = calculate(value)
        )
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
    data class Success (
        val id: UUID = UUID.randomUUID(),
        val timestamp: Long,
        val dateTimeBerlin: String,
        val inputValue: Int,
        val outputValue: String
    ): FizzBuzzResponse()
}