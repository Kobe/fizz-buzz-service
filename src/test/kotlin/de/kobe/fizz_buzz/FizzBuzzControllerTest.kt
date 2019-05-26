package de.kobe.fizz_buzz

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import org.assertj.core.api.KotlinAssertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FizzBuzzControllerTest {

    private val fizzBuzzService: FizzBuzzService = mock()

    private val fizzBuzzController = FizzBuzzController(fizzBuzzService)

    @Test
    fun `test controller`() {
        // given
        doReturn("foo").`when`(fizzBuzzService).calculate(any())
        // when
        val result = fizzBuzzController.fizzBuzz(any())
        // then
        assertThat(result).isNotNull()
        assertThat(result).isEqualTo("foo")
    }
}