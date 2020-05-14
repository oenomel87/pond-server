package dev.dane.pondserver.event.domain.repository

import javafx.event.Event
import org.springframework.data.repository.CrudRepository

interface EventRepository : CrudRepository<Event, Long>