package de.kobe.fizz_buzz

import org.assertj.core.api.KotlinAssertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@DataJpaTest
class FizzBuzzRepositoryIT (

    @Autowired
    val entityManager: TestEntityManager,

    @Autowired
    val fizzBuzzRepository: FizzBuzzRepository) {

    @Test
    fun `returns fizz buzz results`() {
        // given
        val fizz = FizzBuzzResult(
            timestamp = 4711,
            dateTimeBerlin = ZonedDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME),
            inputValue = 3,
            outputValue = "fizz"
        )
        val buzz = FizzBuzzResult(
            timestamp = 4711,
            dateTimeBerlin = ZonedDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME),
            inputValue = 5,
            outputValue = "buzz"
        )
        val fizzBuzz = FizzBuzzResult(
            timestamp = 4711,
            dateTimeBerlin = ZonedDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME),
            inputValue = 15,
            outputValue = "fizz buzz"
        )

        // Using entityManager.merge() instead of entityManager.persist() because
        // FizzBuzzResult has a predefined ID (UUID.randomUUID()) which causes
        // Hibernate to throw PersistentObjectException when using persist() with an entity
        // that already has an ID set.
        // when
        entityManager.merge(fizz)
        entityManager.merge(buzz)
        entityManager.merge(fizzBuzz)
        entityManager.flush()

        // then
        assertThat(fizzBuzzRepository.findAll()).hasSize(3)
    }
}
