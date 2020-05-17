package dev.dane.pondserver.event.domain.repository

import dev.dane.pondserver.event.domain.entity.Event
import dev.dane.pondserver.user.domain.entity.User
import org.springframework.data.repository.CrudRepository

interface EventRepository : CrudRepository<Event, Long> {

    fun findByUser(user : User)
}