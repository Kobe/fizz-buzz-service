package de.kobe.fizz_buzz

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import reactor.core.publisher.Mono

class FizzBuzzServiceTest {

    private val worldClockClient = mock<WorldClockClient> {
        on { getCurrentBerlinTime() } doReturn Mono.just(WorldClock("unknown", Long.MAX_VALUE))
    }

    private val fizzBuzzService = FizzBuzzService(worldClockClient)

    private val negativeValue = -1
    private val regularValue = 1
    private val fizzValue = 3
    private val buzzValue = 5
    private val fizzBuzzValue = 15

    @Test
    fun `returns input value`() {
        assertThat(fizzBuzzService.getFizzBuzzResult(regularValue).outputValue).isEqualTo("1")
    }

    @Test
    fun `returns negative input value`() {
        assertThat(fizzBuzzService.getFizzBuzzResult(negativeValue).outputValue).isEqualTo("-1")
    }

    @Test
    fun `returns 'Fizz'`() {
        assertThat(fizzBuzzService.getFizzBuzzResult(fizzValue).outputValue).isEqualTo("Fizz")
    }

    @Test
    fun `returns 'Buzz'`() {
        assertThat(fizzBuzzService.getFizzBuzzResult(buzzValue).outputValue).isEqualTo("Buzz")
    }

    @Test
    fun `returns 'Fizz Buzz'`() {
        assertThat(fizzBuzzService.getFizzBuzzResult(fizzBuzzValue).outputValue).isEqualTo("Fizz Buzz")
    }

}
