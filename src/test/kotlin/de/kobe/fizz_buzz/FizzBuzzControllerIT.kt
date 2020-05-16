package de.kobe.fizz_buzz

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*


@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class FizzBuzzControllerIT {

    val negativeValue = -1
    val regularValue = 1
    val fizzValue = 3
    val buzzValue = 5
    val fizzBuzzValue = 15

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `returns input value`() {
        mockMvc.perform(get("/fizz-buzz/$regularValue"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(jsonPath("$.value").value(regularValue))
                .andExpect(jsonPath("$.result").value(regularValue.toString()))
    }

    @Test
    fun `can handle invalid parameter`() {
        mockMvc.perform(get("/fizz-buzz/fizz"))
                .andExpect(status().isBadRequest)
    }

    @Test
    fun `returns negative input value`() {
        mockMvc.perform(get("/fizz-buzz/$negativeValue"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(jsonPath("$.value").value(negativeValue))
                .andExpect(jsonPath("$.result").value(negativeValue.toString()))
    }

    @Test
    fun `returns 'Fizz'`() {
        mockMvc.perform(get("/fizz-buzz/$fizzValue"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(jsonPath("$.value").value(fizzValue))
                .andExpect(jsonPath("$.result").value("Fizz"))
    }

    @Test
    fun `returns 'Buzz'`() {
        mockMvc.perform(get("/fizz-buzz/$buzzValue"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(jsonPath("$.value").value(buzzValue))
                .andExpect(jsonPath("$.result").value("Buzz"))
    }

    @Test
    fun `returns 'Fizz Buzz'`() {
        mockMvc.perform(get("/fizz-buzz/$fizzBuzzValue"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(jsonPath("$.value").value(fizzBuzzValue))
                .andExpect(jsonPath("$.result").value("Fizz Buzz"))
    }

}
