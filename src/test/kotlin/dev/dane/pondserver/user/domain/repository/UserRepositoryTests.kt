package dev.dane.pondserver.user.domain.repository

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest
class UserRepositoryTests @Autowired constructor(val userRepository: UserRepository) {

    @Test
    fun `When findAll then return user list`() {
        val users = this.userRepository.findAll()
        assertThat(users).isNotEmpty
    }
}
