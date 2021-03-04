package de.kobe.fizz_buzz

import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
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

    @ApiOperation(value = "get fizz buzz results")
    @GetMapping(value = [""], produces = [APPLICATION_JSON_VALUE])
    fun getFizzBuzzResults(): MutableIterable<FizzBuzzResponse.Success> {
        return fizzBuzzService.getFizzBuzzResults()
    }

    @ApiOperation(value = "get fizz buzz calculation result")
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "successful request"),
        ApiResponse(code = 204, message = "empty result due to failing worldtimeapi.org request"),
        ApiResponse(code = 400, message = "value is not a number"),
    ])
    @GetMapping(value = ["/{value}"], produces = [APPLICATION_JSON_VALUE])
    fun getFizzBuzzResult(@PathVariable value: Int): ResponseEntity<FizzBuzzResponse.Success> {
        val fizzBuzzResponse = fizzBuzzService.getFizzBuzzResult(value)

        return when (fizzBuzzResponse) {
            is FizzBuzzResponse.Failure -> {
                ResponseEntity.noContent().build()
            }
            is FizzBuzzResponse.Success -> {
                ResponseEntity.ok().body(fizzBuzzResponse)
            }
        }
    }

}
