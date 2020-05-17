package dev.dane.pondserver.dummy

import dev.dane.pondserver.category.domain.entity.Category
import dev.dane.pondserver.category.domain.repository.CategoryRepository
import dev.dane.pondserver.core.util.UsercodeGenerator
import dev.dane.pondserver.event.domain.entity.Event
import dev.dane.pondserver.event.domain.repository.EventRepository
import dev.dane.pondserver.user.domain.entity.User
import dev.dane.pondserver.user.domain.repository.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.LocalDateTime
import kotlin.random.Random

@Transactional
@ExtendWith(SpringExtension::class)
@SpringBootTest
class DummyCreator constructor(@Autowired private val userRepository: UserRepository,
                               @Autowired private val categoryRepository: CategoryRepository,
                               @Autowired private val eventRepository: EventRepository) {

    @Test
    fun createDummy() {
        val user = this.createDummyUser()
        val categories = ArrayList<Category>()
        for(i in 0..3) {
            val events = ArrayList<Event>()
            val category = this.createDummyCategory(user, "cate-" + 1)
            categories.add(category)

            for(j in 0..Random.nextInt(1, 10)) {
                val event = this.createDummyEvent(user, category, "c" + i + "e-" +j)
                events.add(event)
            }
        }

        println(user.userCode)
        assertThat(categories.size).isGreaterThan(0)
    }

    fun createDummyUser() : User {
        val user = User(username = "dummy-user",
                name = "dummy",
                password = "dummy1234",
                userCode = UsercodeGenerator().generateUsercode(),
                createdDate = LocalDateTime.now())
        this.userRepository.save(user)
        return user
    }

    fun createDummyCategory(user: User, name: String) : Category {
        val category = Category(name = name, user = user, createdDate = LocalDateTime.now())
        this.categoryRepository.save(category)
        return category
    }

    fun createDummyEvent(user: User, category: Category, name: String) : Event {
        val event = Event(name = name,
                        amount = Random.nextLong(100, 100000),
                        user = user,
                        category = category,
                        eventDate = LocalDate.now(),
                        createdDate = LocalDateTime.now())
        this.eventRepository.save(event)
        return event
    }
}