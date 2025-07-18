package de.kobe.fizz_buzz

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/fizz-buzz")
class FizzBuzzController(
    private val fizzBuzzService: FizzBuzzService
) {

    @Operation(summary = "get fizz buzz results")
    @GetMapping(value = ["/"], produces = [APPLICATION_JSON_VALUE])
    fun getFizzBuzzResults(): Iterable<FizzBuzzResult> {
        return fizzBuzzService.getFizzBuzzResults()
    }

    @Operation(summary = "get fizz buzz calculation result")
    @ApiResponse(responseCode = "200", description = "successful request")
    @ApiResponse(responseCode = "400", description = "value is not a number")
    @ApiResponse(responseCode = "503", description = "service unavailable due to failing worldtimeapi.org request")
    @GetMapping(value = ["/{value}"], produces = [APPLICATION_JSON_VALUE])
    fun getFizzBuzzResult(@PathVariable value: Int): FizzBuzzResponse.Success {
        val fizzBuzzResponse = fizzBuzzService.calculateFizzBuzzResult(value)

        return when (fizzBuzzResponse) {
            is FizzBuzzResponse.Failure -> {
                throw FizzBuzzException.WorldClockApiException()
            }
            is FizzBuzzResponse.Success -> {
                fizzBuzzResponse
            }
        }
    }

}
