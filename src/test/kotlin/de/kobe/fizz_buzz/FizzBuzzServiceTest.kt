package de.kobe.fizz_buzz

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import reactor.core.publisher.Mono

class FizzBuzzServiceTest {

    private val worldClockClient = mock<WorldClockClient> {
        on { getCurrentBerlinTime() } doReturn Mono.just(WorldClock("aValidDateTime", 4711))
    }

    private val fizzBuzzRepository = mock<FizzBuzzRepository> {}

    private val fizzBuzzService = FizzBuzzService(worldClockClient, fizzBuzzRepository)

    private val negativeValue = -1
    private val regularValue = 1
    private val fizzValue = 3
    private val buzzValue = 5
    private val fizzBuzzValue = 15

    @Test
    fun `returns input value`() {
        assertThat(fizzBuzzService.calculateFizzBuzzResult(regularValue))
            .hasFieldOrPropertyWithValue("inputValue", 1)
            .hasFieldOrPropertyWithValue("outputValue", "1")
    }

    @Test
    fun `returns negative input value`() {
        assertThat(fizzBuzzService.calculateFizzBuzzResult(negativeValue))
            .hasFieldOrPropertyWithValue("inputValue", -1)
            .hasFieldOrPropertyWithValue("outputValue", "-1")
    }

    @Test
    fun `returns 'Fizz'`() {
        assertThat(fizzBuzzService.calculateFizzBuzzResult(fizzValue))
            .hasFieldOrPropertyWithValue("inputValue", 3)
            .hasFieldOrPropertyWithValue("outputValue", "Fizz")
    }

    @Test
    fun `returns 'Buzz'`() {
        assertThat(fizzBuzzService.calculateFizzBuzzResult(buzzValue))
            .hasFieldOrPropertyWithValue("inputValue", 5)
            .hasFieldOrPropertyWithValue("outputValue", "Buzz")
    }

    @Test
    fun `returns 'Fizz Buzz'`() {
        assertThat(fizzBuzzService.calculateFizzBuzzResult(fizzBuzzValue))
            .hasFieldOrPropertyWithValue("inputValue", 15)
            .hasFieldOrPropertyWithValue("outputValue", "Fizz Buzz")
    }

}
