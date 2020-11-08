package de.kobe.fizz_buzz

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class FizzBuzzServiceTest {

    private val fizzBuzzService = FizzBuzzService()

    private val negativeValue = -1
    private val regularValue = 1
    private val fizzValue = 3
    private val buzzValue = 5
    private val fizzBuzzValue = 15

    @Test
    fun `returns input value`() {
        assertThat(fizzBuzzService.calculate(regularValue)).isEqualTo(regularValue.toString())
    }

    @Test
    fun `returns negative input value`() {
        assertThat(fizzBuzzService.calculate(negativeValue)).isEqualTo(negativeValue.toString())
    }

    @Test
    fun `returns 'Fizz'`() {
        assertThat(fizzBuzzService.calculate(fizzValue)).isEqualTo("Fizz")
    }

    @Test
    fun `returns 'Buzz'`() {
        assertThat(fizzBuzzService.calculate(buzzValue)).isEqualTo("Buzz")
    }

    @Test
    fun `returns 'Fizz Buzz'`() {
        assertThat(fizzBuzzService.calculate(fizzBuzzValue)).isEqualTo("Fizz Buzz")
    }
}
