package dev.dane.pondserver.event.domain.repository

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest
class EventRepositoryTests @Autowired constructor(private val eventRepository: EventRepository) {

    @Test
    fun `When findAll then return event list` () {
        val events = this.eventRepository.findAll()
        assertThat(events).isNotNull
    }
}