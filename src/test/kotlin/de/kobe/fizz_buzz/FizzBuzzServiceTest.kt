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

    private val fizzBuzzCalculationService = FizzBuzzCalculationService()

    private val fizzBuzzRepository = mock<FizzBuzzRepository> {}

    private val fizzBuzzService = FizzBuzzService(
        worldClockClient,
        fizzBuzzCalculationService,
        fizzBuzzRepository
    )

    @Test
    fun `returns proper fizz buzz result`() {
        assertThat(fizzBuzzService.calculateFizzBuzzResult(1))
            .hasFieldOrPropertyWithValue("inputValue", 1)
            .hasFieldOrPropertyWithValue("outputValue", "1")
    }
}
