package de.kobe.fizz_buzz

import org.springframework.stereotype.Service

@Service
class FizzBuzzService {

    fun calculate(value: Int): String {
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
