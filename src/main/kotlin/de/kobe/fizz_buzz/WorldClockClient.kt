package de.kobe.fizz_buzz

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.codec.DecodingException
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClientResponseException
import reactor.core.publisher.Mono
import java.net.ConnectException
import java.time.Duration
import java.util.concurrent.TimeoutException

@Service
class WorldClockClient(
        @Value("\${services.worldclockapi.url}")
        private val worldClockClientUrl: String
) {

    private val worldClockClient: WebClient by lazy {
        WebClient
                .builder()
                .baseUrl(worldClockClientUrl)
                .build()
    }

    /**
     * Gets the current time in Berlin from the World Clock API.
     *
     * @return A Mono containing a WorldClockResult which can be either Success or one of the Error subtypes
     */
    fun getCurrentBerlinTime(): Mono<WorldClockResult> {
        return worldClockClient
                .get()
                .uri("/Europe/Berlin.json")
                .accept(MediaType.APPLICATION_JSON)
                .header("User-Agent", "fizz buzz service")
                .retrieve()
                .bodyToMono(WorldClock::class.java)
                .timeout(Duration.ofMillis(1000))
                .map { worldClock ->
                    if (worldClock.datetime.isEmpty() && worldClock.unixtime == 0L) {
                        WorldClockResult.Error.MissingFieldsError
                    } else {
                        WorldClockResult.Success(worldClock)
                    }
                }
                .onErrorResume { error ->
                    when (error) {
                        is TimeoutException -> Mono.just(WorldClockResult.Error.TimeoutError)
                        is ConnectException -> Mono.just(WorldClockResult.Error.NetworkError(error))
                        is WebClientResponseException -> Mono.just(WorldClockResult.Error.NetworkError(error))
                        is DecodingException -> Mono.just(WorldClockResult.Error.MissingFieldsError)
                        else -> Mono.just(WorldClockResult.Error.UnknownError(error))
                    }
                }
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
data class WorldClock (
        @JsonProperty("datetime")
        val datetime: String = "",
        @JsonProperty("unixtime")
        val unixtime: Long = 0,
)
