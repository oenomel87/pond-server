package dev.dane.pondserver.event

import dev.dane.pondserver.core.common.enum.EventType
import dev.dane.pondserver.event.domain.entity.Event
import dev.dane.pondserver.event.domain.repository.EventRepository
import dev.dane.pondserver.user.domain.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class EventService (private val eventRepository: EventRepository,
                    private val userRepository: UserRepository) {

    fun sumTotalEventAmount(username : String) : Long {
        val user = this.userRepository.findByUsername(username)
        val events = user?.events
        val sum = 0L
        if(events == null || events.isEmpty()) {
            return sum
        }

        return events.parallelStream()
                .mapToLong{ e -> this.getSum(e) }
                .sum()
    }

    private fun getSum(e : Event) : Long {
        if(EventType.INCOME.equals(e.type)) {
            return e.amount
        }
        return -1 * e.amount
    }
}