package de.kobe.fizz_buzz

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock.*
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import org.assertj.core.api.KotlinAssertions
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class WorldClockClientIT {

    private lateinit var wireMockServer: WireMockServer
    private lateinit var worldClockClient: WorldClockClient

    @BeforeEach
    fun setup() {
        wireMockServer = WireMockServer(WireMockConfiguration.options().dynamicPort())
        wireMockServer.start()

        val worldClockApiUrl = "http://localhost:${wireMockServer.port()}"
        worldClockClient = WorldClockClient(worldClockApiUrl)
    }

    @AfterEach
    fun tearDown() {
        wireMockServer.resetAll()
        wireMockServer.shutdown()
    }

    @Test
    fun `can fetch current date`() {
        // given
        wireMockServer.stubFor(
                get(urlEqualTo("/timezone/Europe/Berlin"))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody("""{"datetime" : "2020-11-14T23:09:11.440323+01:00","unixtime": 1605391751}""")))
        // when
        val worldApiResponse = worldClockClient.getCurrentBerlinTime().block()!!

        // then
        KotlinAssertions.assertThat(worldApiResponse.datetime).isEqualTo("2020-11-14T23:09:11.440323+01:00")
        KotlinAssertions.assertThat(worldApiResponse.unixtime).isEqualTo(1605391751)

    }

    @Test
    fun `can handle unknown fields`() {
        // given
        wireMockServer.stubFor(
                get(urlEqualTo("/timezone/Europe/Berlin"))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody("""{"datetime" : "2020-11-14T23:09:11.440323+01:00","unixtime": 1605391751,"foo": "bar"}""")))
        // when
        val worldApiResponse = worldClockClient.getCurrentBerlinTime().block()!!

        // then
        KotlinAssertions.assertThat(worldApiResponse.datetime).isEqualTo("2020-11-14T23:09:11.440323+01:00")
        KotlinAssertions.assertThat(worldApiResponse.unixtime).isEqualTo(1605391751)

    }

    @Test
    fun `can handle missing fields`() {
        // given
        wireMockServer.stubFor(
                get(urlEqualTo("/timezone/Europe/Berlin"))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody("""{"foo" : "bar"}""")))
        // when
        val worldApiResponse = worldClockClient.getCurrentBerlinTime().block()!!

        // then
        KotlinAssertions.assertThat(worldApiResponse.datetime).isEqualTo("unknown")
        KotlinAssertions.assertThat(worldApiResponse.unixtime).isEqualTo(0)

    }

    @Test
    fun `can handle timeout`() {
        // given
        wireMockServer.stubFor(
                get(urlEqualTo("/timezone/Europe/Berlin"))
                        .willReturn(aResponse()
                                .withFixedDelay(2000)
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody("""{"datetime" : "2020-11-14T23:09:11.440323+01:00","unixtime": 1605391751}""")))
        // when
        val worldApiResponse = worldClockClient.getCurrentBerlinTime().block()!!

        // then
        KotlinAssertions.assertThat(worldApiResponse.datetime).isEqualTo("unknown")
        KotlinAssertions.assertThat(worldApiResponse.unixtime).isEqualTo(0)

    }
}