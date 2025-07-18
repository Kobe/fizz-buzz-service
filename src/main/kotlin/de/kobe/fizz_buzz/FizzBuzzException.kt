package de.kobe.fizz_buzz

/**
 * Exception thrown when there's an issue with FizzBuzz operations.
 */
sealed class FizzBuzzException(message: String) : RuntimeException(message) {

    /**
     * Exception thrown when the World Clock API call fails.
     */
    class WorldClockApiException : FizzBuzzException("Failed to get current time from World Clock API")
}
