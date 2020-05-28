package dev.dane.pondserver.event.service

import dev.dane.pondserver.event.EventService
import dev.dane.pondserver.event.domain.repository.EventRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.transaction.annotation.Transactional

@Transactional
@ExtendWith(SpringExtension::class)
@SpringBootTest
class EventServiceTests @Autowired constructor(private val eventService: EventService,
                                               private val eventRepository: EventRepository) {

    @Test
    fun sumTotalEventAmountTest() {
        val answer = 803156L
        val result = this.eventService.sumTotalEventAmount("dummy-user")
        Assertions.assertEquals(answer, result)
    }
}