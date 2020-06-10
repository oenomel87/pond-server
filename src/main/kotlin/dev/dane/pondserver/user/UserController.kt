package dev.dane.pondserver.user

import dev.dane.pondserver.core.common.model.response.BasicResponse
import dev.dane.pondserver.user.domain.entity.User
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
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

    @PostMapping("/signup")
    fun signup(@RequestBody user: User,
               request: HttpServletRequest,
               response: HttpServletResponse,
               principal: Principal?) : BasicResponse<String> {
        if(principal != null) {
            response.status = HttpStatus.FORBIDDEN.value()
            return BasicResponse("$response.status", "Already signup", null)
        }

        val result = this.userService.signupUser(user)

        if(this.userService.signupUser(user) == "SUCCESS") {
            return BasicResponse("200", "", null)
        }

        return BasicResponse("400", result, null)
    }
}