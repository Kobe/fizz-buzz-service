package de.kobe.fizz_buzz

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

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
        val worldClockResult = worldClockClient.getCurrentBerlinTime().block()

        if (worldClockResult == null) {
            return FizzBuzzResponse.Failure
        }

        return when (worldClockResult) {
            is WorldClockResult.Success -> {
                val worldClock = worldClockResult.worldClock
                val fizzBuzzResult = FizzBuzzResult(
                    timestamp = worldClock.unixtime,
                    dateTimeBerlin = worldClock.datetime,
                    inputValue = value,
                    outputValue = fizzBuzzCalculationService.calculate(value)
                )

                fizzBuzzRepository.save(fizzBuzzResult)
                fizzBuzzResult.toFizzBuzzResponse()
            }
            is WorldClockResult.Error -> {
                // Log the error message for debugging
                // logger.error("Error getting Berlin time: ${worldClockResult.message}")
                FizzBuzzResponse.Failure
            }
        }
    }
}
