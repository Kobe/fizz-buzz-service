package de.kobe.fizz_buzz

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*
import jakarta.persistence.Entity
import jakarta.persistence.Id

// https://spring.io/guides/tutorials/spring-boot-kotlin/
@Repository
interface FizzBuzzRepository: CrudRepository<FizzBuzzResult, UUID> {}

@Entity
data class FizzBuzzResult (
    @Id
    val id: UUID = UUID.randomUUID(),
    val timestamp: Long,
    val dateTimeBerlin: String,
    val inputValue: Int,
    val outputValue: String
)

fun FizzBuzzResult.toFizzBuzzResponse(): FizzBuzzResponse {
    return FizzBuzzResponse.Success(
        id,
        timestamp,
        dateTimeBerlin,
        inputValue,
        outputValue
    )
}
