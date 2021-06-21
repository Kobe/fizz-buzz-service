package de.kobe.fizz_buzz

import org.assertj.core.api.KotlinAssertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
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

        // https://stackoverflow.com/questions/15198675/javax-persistence-persistenceexception-org-hibernate-persistentobjectexception
        // TODO why entityManager.persist doesn't work here?
        // when
        entityManager.merge(fizz)
        entityManager.merge(buzz)
        entityManager.merge(fizzBuzz)
        entityManager.flush()

        // then
        assertThat(fizzBuzzRepository.findAll()).hasSize(3)
    }
}