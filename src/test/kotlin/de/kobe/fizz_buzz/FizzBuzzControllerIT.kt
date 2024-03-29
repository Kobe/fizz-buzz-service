package de.kobe.fizz_buzz

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import javax.sql.DataSource


@ExtendWith(SpringExtension::class)
@SpringBootTest
@AutoConfigureMockMvc
class FizzBuzzControllerIT {

    private lateinit var wireMockServer: WireMockServer

    @BeforeEach
    fun setup() {
        wireMockServer = WireMockServer(WireMockConfiguration.options().port(8081))
        wireMockServer.start()

        wireMockServer.stubFor(
            WireMock.get(WireMock.urlEqualTo("/Europe/Berlin.json"))
                .willReturn(
                    WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""{"datetime" :"2021-01-31T17:06:09.172975+01:00","unixtime":1605391751}""")
                )
        )
    }

    @AfterEach
    fun tearDown() {
        wireMockServer.stop()
    }

    @Nested
    @TestPropertySource(properties = ["services.worldclockapi.url=http://localhost:8081"])
    inner class FizzBuzzResults {

        @Autowired
        private lateinit var mockMvc: MockMvc

        @Autowired
        private lateinit var dataSource: DataSource

        @AfterEach
        fun cleanUp() {
            dataSource.connection.use { connection ->
                connection.prepareStatement("DELETE FROM fizz_buzz_result").execute()
            }
        }

        @Test
        fun `can handle missing results`() {
            mockMvc.perform(get("/fizz-buzz/"))
                .andExpect(status().isOk)
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$").isEmpty)
        }

        @Test
        fun `can return existing results`() {
            // given
            mockMvc.perform(get("/fizz-buzz/1"))
            mockMvc.perform(get("/fizz-buzz/3"))
            mockMvc.perform(get("/fizz-buzz/5"))
            mockMvc.perform(get("/fizz-buzz/15"))

            // then
            mockMvc.perform(get("/fizz-buzz/"))
                .andExpect(status().isOk)
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$").isNotEmpty)
        }


    }

    @Nested
    @TestPropertySource(properties = ["services.worldclockapi.url=http://localhost:8081"])
    inner class FizzBuzzCalculation {

        private val negativeValue = -1
        private val validValue = 1
        private val invalidValue = "fizz"
        private val fizzValue = 3

        @Autowired
        private lateinit var mockMvc: MockMvc

        @Autowired
        private lateinit var dataSource: DataSource

        @AfterEach
        fun cleanUp() {
            dataSource.connection.use { connection ->
                connection.prepareStatement("DELETE FROM fizz_buzz_result").execute()
            }
        }

        @Test
        fun `can handle valid parameter`() {
            mockMvc.perform(get("/fizz-buzz/$validValue"))
                    .andExpect(status().isOk)
                    .andExpect(content().contentType(APPLICATION_JSON))
        }

        @Test
        fun `can handle invalid parameter`() {
            mockMvc.perform(get("/fizz-buzz/$invalidValue"))
                    .andExpect(status().isBadRequest)
        }

        @Test
        fun `can handle negative number as parameter`() {
            mockMvc.perform(get("/fizz-buzz/$negativeValue"))
                    .andExpect(status().isOk)
        }

        @Test
        fun `can return fizz result object`() {
            mockMvc.perform(get("/fizz-buzz/$fizzValue"))
                .andExpect(jsonPath("$.timestamp").value(1605391751))
                .andExpect(jsonPath("$.dateTimeBerlin").value("2021-01-31T17:06:09.172975+01:00"))
                .andExpect(jsonPath("$.inputValue").value(3))
                .andExpect(jsonPath("$.outputValue").value("Fizz"))
        }

    }

}
