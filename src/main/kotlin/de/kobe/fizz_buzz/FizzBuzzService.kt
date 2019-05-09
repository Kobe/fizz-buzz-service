package de.kobe.fizz_buzz

import org.springframework.stereotype.Service

@Service
class FizzBuzzService {

    fun calculate(value: Int): String {
        return when {
            value % 15 == 0 -> "$value fizz buzz"
            value % 3 == 0 -> "$value fizz"
            value % 5 == 0 -> "$value buzz"
            else -> "$value"
        }
    }
}
