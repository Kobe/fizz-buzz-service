package de.kobe.fizz_buzz

import org.assertj.core.api.KotlinAssertions
import org.junit.jupiter.api.Test

class FizzBuzzControllerTest {

    private val fizzBuzzService = FizzBuzzService()
    private val fizzBuzzController = FizzBuzzController(fizzBuzzService)

    private val negativeValue = -1
    private val regularValue = 1
    private val fizzValue = 3
    private val buzzValue = 5
    private val fizzBuzzValue = 15

    @Test
    fun `returns input value`() {
        // given
        val actual = fizzBuzzController.getFizzBuzzResult(regularValue)
        val expected = FizzBuzzResult(value = regularValue, result = "$regularValue")

        // then
        KotlinAssertions.assertThat(actual.value).isEqualTo(expected.value)
        KotlinAssertions.assertThat(actual.result).isEqualTo(expected.result)
    }

    @Test
    fun `returns negative input value`() {
        // given
        val actual = fizzBuzzController.getFizzBuzzResult(negativeValue)
        val expected = FizzBuzzResult(value = negativeValue, result = "$negativeValue")

        // then
        KotlinAssertions.assertThat(actual.value).isEqualTo(expected.value)
        KotlinAssertions.assertThat(actual.result).isEqualTo(expected.result)
    }

    @Test
    fun `returns 'Fizz'`() {
        // given
        val actual = fizzBuzzController.getFizzBuzzResult(fizzValue)
        val expected = FizzBuzzResult(value = fizzValue, result = "Fizz")

        // then
        KotlinAssertions.assertThat(actual.value).isEqualTo(expected.value)
        KotlinAssertions.assertThat(actual.result).isEqualTo(expected.result)
    }

    @Test
    fun `returns 'Buzz'`() {
        // given
        val actual = fizzBuzzController.getFizzBuzzResult(buzzValue)
        val expected = FizzBuzzResult(value = buzzValue, result = "Buzz")

        // then
        KotlinAssertions.assertThat(actual.value).isEqualTo(expected.value)
        KotlinAssertions.assertThat(actual.result).isEqualTo(expected.result)
    }

    @Test
    fun `returns 'FizzBuzz'`() {
        // given
        val actual = fizzBuzzController.getFizzBuzzResult(fizzBuzzValue)
        val expected = FizzBuzzResult(value = fizzBuzzValue, result = "Fizz Buzz")

        // then
        KotlinAssertions.assertThat(actual.value).isEqualTo(expected.value)
        KotlinAssertions.assertThat(actual.result).isEqualTo(expected.result)
    }
}
