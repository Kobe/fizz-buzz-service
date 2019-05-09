package de.kobe.fizz_buzz

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/fizz-buzz")
class FizzBuzzController (private val fizzBuzzService: FizzBuzzService) {

    @GetMapping(value = ["/{value}"])
    @ResponseBody
    fun fizzBuzz(@PathVariable value: Int): String {
        return fizzBuzzService.calculate(value)
    }
}