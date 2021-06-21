package de.kobe.fizz_buzz

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class FizzBuzzService (
    private val worldClockClient: WorldClockClient,
    private val fizzBuzzCalculationService: FizzBuzzCalculationService,
    private val fizzBuzzRepository: FizzBuzzRepository
) {

    @Transactional
    fun getFizzBuzzResults(): Iterable<FizzBuzzResult> {
        return fizzBuzzRepository.findAll()
    }

    @Transactional
    fun calculateFizzBuzzResult(value: Int): FizzBuzzResponse {
        val currentBerlinTime = worldClockClient.getCurrentBerlinTime().block()

        if (currentBerlinTime == null || currentBerlinTime.datetime.isEmpty()) {
            return FizzBuzzResponse.Failure
        }

        val fizzBuzzResult = FizzBuzzResult(
            timestamp = currentBerlinTime.unixtime,
            dateTimeBerlin = currentBerlinTime.datetime,
            inputValue = value,
            outputValue = fizzBuzzCalculationService.calculate(value)
        )


        fizzBuzzRepository.save(fizzBuzzResult)

        return fizzBuzzResult.toFizzBuzzResponse()

    }


}

sealed class FizzBuzzResponse {

    object Failure: FizzBuzzResponse()

    data class Success (
        val id: UUID = UUID.randomUUID(),
        val timestamp: Long,
        val dateTimeBerlin: String,
        val inputValue: Int,
        val outputValue: String
    ): FizzBuzzResponse()

}