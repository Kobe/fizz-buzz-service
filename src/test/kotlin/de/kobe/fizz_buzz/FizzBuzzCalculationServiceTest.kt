package de.kobe.fizz_buzz

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class FizzBuzzCalculationServiceTest {

    private val fizzBuzzCalculationService = FizzBuzzCalculationService()

    private val negativeValue = -1
    private val regularValue = 1
    private val fizzValue = 3
    private val buzzValue = 5
    private val fizzBuzzValue = 15

    @Test
    fun `can handle ordinary number`() {
        Assertions.assertThat(fizzBuzzCalculationService.calculate(regularValue)).isEqualTo("1")
    }

    @Test
    fun `can handle ordinary negative number`() {
        Assertions.assertThat(fizzBuzzCalculationService.calculate(negativeValue)).isEqualTo("-1")
    }

    @Test
    fun `can calculate 'Fizz'`() {
        Assertions.assertThat(fizzBuzzCalculationService.calculate(fizzValue)).isEqualTo("Fizz")
    }

    @Test
    fun `can calculate 'Buzz'`() {
        Assertions.assertThat(fizzBuzzCalculationService.calculate(buzzValue))
            .isEqualTo("Buzz")
    }

    @Test
    fun `can calculate 'Fizz Buzz'`() {
        Assertions.assertThat(fizzBuzzCalculationService.calculate(fizzBuzzValue)).isEqualTo("Fizz Buzz")
    }
}