package dev.dane.pondserver.user

import dev.dane.pondserver.user.domain.entity.User
import dev.dane.pondserver.user.domain.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService (private val userRepository : UserRepository) {

    fun fetchUser (username : String) : User? {
        return this.userRepository.findByUsername(username)
    }
}