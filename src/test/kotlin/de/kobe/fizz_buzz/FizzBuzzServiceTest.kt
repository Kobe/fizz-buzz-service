package de.kobe.fizz_buzz

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

class FizzBuzzServiceTest {

    val fizzBuzzService = FizzBuzzService()

    val fizzValue = 3
    val buzzValue = 5
    val fizzBuzzValue = 15

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
