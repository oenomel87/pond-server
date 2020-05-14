package dev.dane.pondserver.user.service

import dev.dane.pondserver.user.domain.entity.User
import dev.dane.pondserver.user.domain.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService constructor(val userRepository : UserRepository) {

    fun fetchUser (username : String) : User? {
        return this.userRepository.findByUsername(username)
    }
}