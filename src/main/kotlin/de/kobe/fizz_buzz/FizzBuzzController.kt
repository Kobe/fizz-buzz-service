package de.kobe.fizz_buzz

import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/fizz-buzz")
class FizzBuzzController (
    private val fizzBuzzService: FizzBuzzService
) {
    @GetMapping(value = ["/{value}"], produces = [APPLICATION_JSON_VALUE])
    fun getFizzBuzzResult(@PathVariable value: Int): FizzBuzzResult {
        return FizzBuzzResult(value = value, result = fizzBuzzService.calculate(value))
    }
}

data class FizzBuzzResult (
    val id: UUID = UUID.randomUUID(),
    val value: Int,
    val result: String
)
