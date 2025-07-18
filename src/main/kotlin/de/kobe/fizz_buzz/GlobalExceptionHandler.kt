package de.kobe.fizz_buzz

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

/**
 * Global exception handler for consistent error handling across all controllers.
 */
@RestControllerAdvice
class GlobalExceptionHandler {

    /**
     * Handles exceptions thrown when a method argument is not the expected type.
     * For example, when a path variable should be an integer but a string is provided.
     *
     * @param ex The exception that was thrown
     * @return A ResponseEntity with an ErrorResponse and HTTP status 400 (Bad Request)
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun handleMethodArgumentTypeMismatch(ex: MethodArgumentTypeMismatchException): ResponseEntity<ErrorResponse> {
        val errorMessage = "Invalid value for parameter '${ex.name}'. Expected ${ex.requiredType?.simpleName}, got '${ex.value}'"
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse(
                status = HttpStatus.BAD_REQUEST.value(),
                error = HttpStatus.BAD_REQUEST.reasonPhrase,
                message = errorMessage,
                timestamp = ZonedDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
            ))
    }

    /**
     * Handles exceptions thrown when the request body cannot be read.
     *
     * @param ex The exception that was thrown
     * @return A ResponseEntity with an ErrorResponse and HTTP status 400 (Bad Request)
     */
    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadable(ex: HttpMessageNotReadableException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse(
                status = HttpStatus.BAD_REQUEST.value(),
                error = HttpStatus.BAD_REQUEST.reasonPhrase,
                message = "Invalid request body: ${ex.message}",
                timestamp = ZonedDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
            ))
    }

    /**
     * Handles FizzBuzzException and its subclasses.
     *
     * @param ex The exception that was thrown
     * @return A ResponseEntity with an ErrorResponse and HTTP status 503 (Service Unavailable)
     */
    @ExceptionHandler(FizzBuzzException::class)
    fun handleFizzBuzzException(ex: FizzBuzzException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.SERVICE_UNAVAILABLE)
            .body(ErrorResponse(
                status = HttpStatus.SERVICE_UNAVAILABLE.value(),
                error = HttpStatus.SERVICE_UNAVAILABLE.reasonPhrase,
                message = ex.message ?: "An error occurred with the FizzBuzz service",
                timestamp = ZonedDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
            ))
    }

    /**
     * Handles all other exceptions that are not explicitly handled by other methods.
     *
     * @param ex The exception that was thrown
     * @return A ResponseEntity with an ErrorResponse and HTTP status 500 (Internal Server Error)
     */
    @ExceptionHandler(Exception::class)
    fun handleGenericException(ex: Exception): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ErrorResponse(
                status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
                error = HttpStatus.INTERNAL_SERVER_ERROR.reasonPhrase,
                message = "An unexpected error occurred: ${ex.message}",
                timestamp = ZonedDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
            ))
    }
}

/**
 * Standard error response format for all API errors.
 *
 * @property status The HTTP status code
 * @property error The HTTP status reason phrase
 * @property message A detailed error message
 * @property timestamp The time when the error occurred
 */
data class ErrorResponse(
    val status: Int,
    val error: String,
    val message: String,
    val timestamp: String
)
