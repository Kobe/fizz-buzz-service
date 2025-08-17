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
                get(urlEqualTo("/Europe/Berlin.json"))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody("""{"datetime" : "2021-01-31T17:06:09.172975+01:00","unixtime": 1605391751}""")))
        // when
        val result = worldClockClient.getCurrentBerlinTime().block()

        // then
        KotlinAssertions.assertThat(result).isInstanceOf(WorldClockResult.Success::class.java)
        val success = result as WorldClockResult.Success
        KotlinAssertions.assertThat(success.worldClock.datetime).isEqualTo("2021-01-31T17:06:09.172975+01:00")
        KotlinAssertions.assertThat(success.worldClock.unixtime).isEqualTo(1605391751)
    }

    @Test
    fun `can handle unknown fields`() {
        // given
        wireMockServer.stubFor(
                get(urlEqualTo("/Europe/Berlin.json"))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody("""{"datetime" : "2021-01-31T17:06:09.172975+01:00","unixtime": 1605391751,"foo": "bar"}""")))
        // when
        val result = worldClockClient.getCurrentBerlinTime().block()

        // then
        KotlinAssertions.assertThat(result).isInstanceOf(WorldClockResult.Success::class.java)
        val success = result as WorldClockResult.Success
        KotlinAssertions.assertThat(success.worldClock.datetime).isEqualTo("2021-01-31T17:06:09.172975+01:00")
        KotlinAssertions.assertThat(success.worldClock.unixtime).isEqualTo(1605391751)
    }

    @Test
    fun `can handle missing fields`() {
        // given
        wireMockServer.stubFor(
                get(urlEqualTo("/Europe/Berlin.json"))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody("""{"foo" : "bar"}""")))
        // when
        val result = worldClockClient.getCurrentBerlinTime().block()

        // then
        KotlinAssertions.assertThat(result).isInstanceOf(WorldClockResult.Error.MissingFieldsError::class.java)
        KotlinAssertions.assertThat((result as WorldClockResult.Error).message).isEqualTo("Response missing required fields")
    }

    @Test
    fun `can handle timeout`() {
        // given
        wireMockServer.stubFor(
                get(urlEqualTo("/Europe/Berlin.json"))
                        .willReturn(aResponse()
                                .withFixedDelay(2000)
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody("""{"datetime" : "2021-01-31T17:06:09.172975+01:00","unixtime": 1605391751}""")))
        // when
        val result = worldClockClient.getCurrentBerlinTime().block()

        // then
        KotlinAssertions.assertThat(result).isInstanceOf(WorldClockResult.Error.TimeoutError::class.java)
        KotlinAssertions.assertThat((result as WorldClockResult.Error).message).isEqualTo("Request timed out")
    }

    @Test
    fun `can handle network error`() {
        // given
        // Instead of stopping the server, we'll configure it to return a 503 status
        // This avoids potential issues with the server not being available for subsequent tests
        wireMockServer.stubFor(
                get(urlEqualTo("/Europe/Berlin.json"))
                        .willReturn(aResponse()
                                .withStatus(503)
                                .withHeader("Content-Type", "application/json")
                                .withBody("""{"error": "Service Unavailable"}""")))

        // when
        val result = worldClockClient.getCurrentBerlinTime().block()

        // then
        KotlinAssertions.assertThat(result).isInstanceOf(WorldClockResult.Error.NetworkError::class.java)
        KotlinAssertions.assertThat((result as WorldClockResult.Error).message).startsWith("Network error:")
    }
}
