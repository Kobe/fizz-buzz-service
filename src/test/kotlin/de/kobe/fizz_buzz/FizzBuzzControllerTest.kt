package de.kobe.fizz_buzz

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class FizzBuzzControllerTest {

    val fizzValue = 3
    val buzzValue = 5
    val fizzBuzzValue = 15

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `test controller with a valid value`() {
        mockMvc.perform(get("/fizz-buzz/$fizzValue"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful)
    }

    @Test
    fun `test controller with an invalid value`() {
        mockMvc.perform(get("/fizz-buzz/fizz"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError)
    }

    @Test
    fun `test fizz case`() {
        mockMvc.perform(get("/fizz-buzz/$fizzValue"))
                .andExpect {
                    MockMvcResultMatchers.status().is2xxSuccessful
                    MockMvcResultMatchers.content().equals("$fizzValue fizz")
                }
    }

    @Test
    fun `test buzz case`() {
        mockMvc.perform(get("/fizz-buzz/$buzzValue"))
                .andExpect {
                    MockMvcResultMatchers.status().is2xxSuccessful
                    MockMvcResultMatchers.content().equals("$buzzValue buzz")
                }
    }

    @Test
    fun `test fizz buzz case`() {
        mockMvc.perform(get("/fizz-buzz/$fizzBuzzValue"))
                .andExpect {
                    MockMvcResultMatchers.status().is2xxSuccessful
                    MockMvcResultMatchers.content().equals("$fizzBuzzValue buzz")
                }
    }

}