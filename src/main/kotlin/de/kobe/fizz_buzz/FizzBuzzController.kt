package de.kobe.fizz_buzz

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/fizz-buzz")
class FizzBuzzController {

    @GetMapping(value = ["/{value}"])
    @ResponseBody
    fun fizzBuzz(@PathVariable value: Int): String {
        return calculate(value)
    }

    private fun calculate(value: Int): String {
        return if (value % 3 == 0 && value % 5 ==0) {
            "$value fizz buzz"
        } else if (value % 3 == 0) {
            "$value fizz"
        } else if (value % 5 == 0) {
            "$value buzz"
        } else {
            "$value"
        }
    }
}