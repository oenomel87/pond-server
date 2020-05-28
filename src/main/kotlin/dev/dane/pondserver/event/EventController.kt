package dev.dane.pondserver.event

import dev.dane.pondserver.core.common.model.response.BasicResponse
import dev.dane.pondserver.user.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal
import javax.servlet.http.HttpServletResponse

@RequestMapping("/event")
@RestController
class EventController (private val eventService: EventService,
                        private val userService: UserService) {

    @GetMapping("/total")
    fun total(response: HttpServletResponse,
              principal: Principal) : BasicResponse<Long> {
        val sum = this.eventService.sumTotalEventAmount(principal.name)
        return BasicResponse("200", "SUCCESS", sum)
    }
}