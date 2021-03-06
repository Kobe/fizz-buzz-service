package de.kobe.fizz_buzz

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import java.time.Duration

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

    fun getCurrentBerlinTime(): Mono<WorldClock> {
        return worldClockClient
                .get()
                .uri("/Europe/Berlin.json")
                .accept(MediaType.APPLICATION_JSON)
                .header("User-Agent", "fizz buzz service")
                .retrieve()
                .bodyToMono(WorldClock::class.java)
                .timeout(Duration.ofMillis(1000))
                .onErrorResume { Mono.just(WorldClock("", 0)) }

    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
data class WorldClock (
        @JsonProperty("datetime")
        val datetime: String,
        @JsonProperty("unixtime")
        val unixtime: Long,
)
