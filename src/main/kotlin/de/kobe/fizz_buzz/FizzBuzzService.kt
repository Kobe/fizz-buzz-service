package de.kobe.fizz_buzz

import org.springframework.stereotype.Service
import java.util.*

@Service
class FizzBuzzService (
    private val worldClockClient: WorldClockClient
) {

    fun getFizzBuzzResult(value: Int): FizzBuzzResult {
        val currentBerlinTime = worldClockClient.getCurrentBerlinTime().block()
            ?: WorldClock("unknown", Long.MAX_VALUE)

        return FizzBuzzResult(
            time = currentBerlinTime.currentDateTime,
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

data class FizzBuzzResult (
    val id: UUID = UUID.randomUUID(),
    val time: String = "unknown",
    val inputValue: Int,
    val outputValue: String
)
