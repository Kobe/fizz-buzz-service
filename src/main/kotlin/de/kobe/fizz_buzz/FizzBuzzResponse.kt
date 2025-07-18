package de.kobe.fizz_buzz

import java.util.*

/**
 * Represents the response from the FizzBuzz service.
 * This sealed class allows for type-safe handling of different response types.
 */
sealed class FizzBuzzResponse {

    /**
     * Represents a failure response when the service cannot complete the calculation.
     */
    object Failure: FizzBuzzResponse()

    /**
     * Represents a successful response containing the FizzBuzz calculation result.
     *
     * @property id Unique identifier for the result
     * @property timestamp Unix timestamp when the calculation was performed
     * @property dateTimeBerlin The date and time in Berlin timezone when the calculation was performed
     * @property inputValue The input value for the FizzBuzz calculation
     * @property outputValue The result of the FizzBuzz calculation
     */
    data class Success (
        val id: UUID = UUID.randomUUID(),
        val timestamp: Long,
        val dateTimeBerlin: String,
        val inputValue: Int,
        val outputValue: String
    ): FizzBuzzResponse()
}
