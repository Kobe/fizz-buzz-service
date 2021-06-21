package de.kobe.fizz_buzz

import org.springframework.stereotype.Service

@Service
class FizzBuzzCalculationService {
    fun calculate(value: Int): String {
        return when {
            value % 15 == 0 -> "Fizz Buzz"
            value % 3 == 0 -> "Fizz"
            value % 5 == 0 -> "Buzz"
            else -> "$value"
        }
    }
}