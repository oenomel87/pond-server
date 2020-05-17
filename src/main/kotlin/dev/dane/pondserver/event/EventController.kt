package dev.dane.pondserver.event

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/event")
@RestController
class EventController (private val eventService: EventService) {

}