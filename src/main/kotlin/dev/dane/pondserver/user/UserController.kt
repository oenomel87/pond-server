package dev.dane.pondserver.user

import dev.dane.pondserver.core.common.model.response.BasicResponse
import dev.dane.pondserver.user.domain.entity.User
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RequestMapping("/user")
@RestController
class UserController (private val userService: UserService) {

    @GetMapping("", "/")
    fun fetchUser(request: HttpServletRequest,
                  response: HttpServletResponse,
                  principal: Principal) : BasicResponse<User> {
        val user = this.userService.fetchUser(principal.name)
        if (user == null) {
            response.status = HttpStatus.NOT_FOUND.value()
            return BasicResponse("$response.status", "Cannot found user", null)
        }
        return BasicResponse("200", "", user)
    }
}