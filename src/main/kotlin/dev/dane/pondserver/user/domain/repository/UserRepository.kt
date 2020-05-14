package dev.dane.pondserver.user.domain.repository

import dev.dane.pondserver.user.domain.entity.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long> {
    fun findByUsername(username : String) : User?
}