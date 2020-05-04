package de.kobe.fizz_buzz

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class FizzBuzzServiceTest {

    private val fizzBuzzService = FizzBuzzService()

    private val fizzValue = 3
    private val buzzValue = 5
    private val fizzBuzzValue = 15

    @Test
    fun `non matching`() {
        assertThat(fizzBuzzService.calculate(1)).isEqualTo("1")
    }

        @Test
    fun `test fizz case`() {
        assertThat(fizzBuzzService.calculate(fizzValue)).isEqualTo("3 fizz")
    }

    @Test
    fun `test buzz case`() {
        assertThat(fizzBuzzService.calculate(buzzValue)).isEqualTo("5 buzz")
    }

    @Test
    fun `test fizzBuzz case`() {
        assertThat(fizzBuzzService.calculate(fizzBuzzValue)).isEqualTo("15 fizz buzz")
    }
}
