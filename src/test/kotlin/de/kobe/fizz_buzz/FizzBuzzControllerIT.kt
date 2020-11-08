package de.kobe.fizz_buzz

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class FizzBuzzControllerIT {

    private val negativeValue = -1
    private val regularValue = 1
    private val fizzValue = 3
    private val buzzValue = 5
    private val fizzBuzzValue = 15

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `returns input value`() {
        mockMvc.perform(get("/fizz-buzz/$regularValue"))
                .andExpect(status().isOk)
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
    }

    @Test
    fun `can handle invalid parameter`() {
        mockMvc.perform(get("/fizz-buzz/fizz"))
                .andExpect(status().isBadRequest)
    }

    @Test
    fun `returns negative input value`() {
        mockMvc.perform(get("/fizz-buzz/$negativeValue"))
                .andExpect(status().isOk)
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
    }

    @Test
    fun `returns 'Fizz'`() {
        mockMvc.perform(get("/fizz-buzz/$fizzValue"))
                .andExpect(status().isOk)
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
    }

    @Test
    fun `returns 'Buzz'`() {
        mockMvc.perform(get("/fizz-buzz/$buzzValue"))
                .andExpect(status().isOk)
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
    }

    @Test
    fun `returns 'Fizz Buzz'`() {
        mockMvc.perform(get("/fizz-buzz/$fizzBuzzValue"))
                .andExpect(status().isOk)
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
    }

}
