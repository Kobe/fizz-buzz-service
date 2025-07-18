package de.kobe.fizz_buzz

/**
 * Represents the result of a call to the World Clock API.
 * This sealed class allows for type-safe handling of different response types.
 */
sealed class WorldClockResult {
    /**
     * Represents a successful response from the World Clock API.
     *
     * @property worldClock The data returned by the World Clock API
     */
    data class Success(val worldClock: WorldClock) : WorldClockResult()

    /**
     * Base class for all error results.
     *
     * @property message A human-readable error message
     */
    sealed class Error(val message: String) : WorldClockResult() {
        /**
         * Represents a network error when calling the World Clock API.
         *
         * @property cause The underlying exception that caused the network error
         */
        data class NetworkError(val cause: Throwable) : Error("Network error: ${cause.message}")

        /**
         * Represents a timeout error when calling the World Clock API.
         */
        object TimeoutError : Error("Request timed out")

        /**
         * Represents an error when the API response is missing required fields.
         */
        object MissingFieldsError : Error("Response missing required fields")

        /**
         * Represents an unknown error when calling the World Clock API.
         *
         * @property cause The underlying exception that caused the error
         */
        data class UnknownError(val cause: Throwable) : Error("Unknown error: ${cause.message}")
    }
}
