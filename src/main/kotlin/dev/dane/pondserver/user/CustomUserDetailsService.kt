package dev.dane.pondserver.user

import dev.dane.pondserver.user.domain.entity.UserPrincipal
import dev.dane.pondserver.user.domain.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(val userRepository: UserRepository) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val user = this.userRepository.findByUsername(username) ?: throw UsernameNotFoundException("Not Found!")
        return UserPrincipal(user)
    }
}