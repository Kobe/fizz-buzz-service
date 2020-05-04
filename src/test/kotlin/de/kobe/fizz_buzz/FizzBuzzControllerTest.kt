package de.kobe.fizz_buzz

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import org.assertj.core.api.KotlinAssertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class FizzBuzzControllerTest {

    private val fizzBuzzService: FizzBuzzService = mock()

    private val fizzBuzzController = FizzBuzzController(fizzBuzzService)

    @Test
    fun `returns JSON for positive values`() {
        // given
        doReturn("1").`when`(fizzBuzzService).calculate(any())
        // when
        val result = fizzBuzzController.getFizzBuzzResult(1)
        // then
        assertThat(result.statusCodeValue).isEqualTo(200)
        assertThat(result.body!!.value).isEqualTo(1)
        assertThat(result.body!!.result).isEqualTo("1")
    }

    @Test
    fun `returns JSON for negative values`() {
        // given
        doReturn("-1").`when`(fizzBuzzService).calculate(any())
        // when
        val result = fizzBuzzController.getFizzBuzzResult(-1)
        // then
        assertThat(result.statusCodeValue).isEqualTo(200)
        assertThat(result.body!!.value).isEqualTo(-1)
        assertThat(result.body!!.result).isEqualTo("-1")
    }
}
