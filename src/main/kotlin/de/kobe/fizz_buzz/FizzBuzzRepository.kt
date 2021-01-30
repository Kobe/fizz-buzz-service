package de.kobe.fizz_buzz

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

// https://spring.io/guides/tutorials/spring-boot-kotlin/
@Repository
interface FizzBuzzRepository: CrudRepository<FizzBuzzResponse.Success, UUID> {}