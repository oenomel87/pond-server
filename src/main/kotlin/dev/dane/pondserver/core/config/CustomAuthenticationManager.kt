package dev.dane.pondserver.core.config

import dev.dane.pondserver.user.UserService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.stereotype.Component

@Component
class CustomAuthenticationManager constructor(val userService : UserService)
    : AuthenticationManager {

    override fun authenticate(authentication: Authentication): Authentication? {
        val passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()
        val username = authentication.name
        val password = authentication.credentials.toString()
        val user = this.userService.fetchUser(username)

        if (user != null && passwordEncoder.matches(password, user.password)) {
            return UsernamePasswordAuthenticationToken(username, password)
        }

        return null
    }
}