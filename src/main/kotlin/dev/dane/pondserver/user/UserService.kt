package dev.dane.pondserver.user

import dev.dane.pondserver.core.util.generateUsercode
import dev.dane.pondserver.user.domain.entity.User
import dev.dane.pondserver.user.domain.repository.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService (private val userRepository : UserRepository) {

    val log: Logger = LoggerFactory.getLogger(UserService::class.java)

    val passwordEncoder: PasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()

    fun fetchUser (username : String) : User? {
        return this.userRepository.findByUsername(username)
    }

    fun signupUser (user : User) : String {
        try {
            user.password = passwordEncoder.encode(user.password)
            user.userCode = generateUsercode()
            this.userRepository.save(user)
        } catch (e: Exception) {
            log.error("Cannot save new user data")
            log.error(e.message ?: "no message")
            return e.message ?: "no message"
        }
        return "SUCCESS"
    }
}