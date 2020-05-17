package dev.dane.pondserver.event

import dev.dane.pondserver.event.domain.repository.EventRepository
import org.springframework.stereotype.Service

@Service
class EventService (private val eventRepository: EventRepository) {

    fun sumTotalEventAmount() {

    }
}