package dev.dane.pondserver.dummy

import dev.dane.pondserver.category.domain.entity.Category
import dev.dane.pondserver.category.domain.repository.CategoryRepository
import dev.dane.pondserver.core.common.enum.EventType
import dev.dane.pondserver.core.util.UsercodeGenerator
import dev.dane.pondserver.event.domain.entity.Event
import dev.dane.pondserver.event.domain.repository.EventRepository
import dev.dane.pondserver.user.domain.entity.User
import dev.dane.pondserver.user.domain.repository.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.LocalDateTime
import kotlin.random.Random

//@Transactional
@ExtendWith(SpringExtension::class)
@SpringBootTest
class DummyCreator constructor(@Autowired private val userRepository: UserRepository,
                            @Autowired private val categoryRepository: CategoryRepository,
                            @Autowired private val eventRepository: EventRepository) {

    @Test
    fun createDummy() {
        this.initDummyData()
        val user = this.createDummyUser()
        val categories = ArrayList<Category>()
        for(i in 0..3) {
            val events = ArrayList<Event>()
            val category = this.createDummyCategory(user, "cate-$i")
            categories.add(category)

            for(j in 0..Random.nextInt(1, 10)) {
                val event = this.createDummyEvent(user, category, "c$i-e-$j")
                events.add(event)
            }
        }

        Assertions.assertTrue(categories.size > 0)
    }

    //@Test
    fun initDummyData() {
        this.eventRepository.deleteAll()
        this.categoryRepository.deleteAll()
        this.userRepository.deleteAll()
    }

    fun createDummyUser() : User {
        val user = User(username = "dummy-user",
                name = "dummy",
                password = this.getEncryptPassword("dummy1234"),
                userCode = UsercodeGenerator().generateUsercode(),
                createdDate = LocalDateTime.now())
        this.userRepository.save(user)
        return user
    }

    @Test
    fun getPassword() {
        val pw = this.getEncryptPassword("dummy1234")
        println(pw)
    }

    fun getEncryptPassword(raw : String) : String {
        val passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()
        return passwordEncoder.encode(raw)
    }

    fun createDummyCategory(user: User, name: String) : Category {
        val category = Category(name = name, user = user, createdDate = LocalDateTime.now())
        this.categoryRepository.save(category)
        return category
    }

    fun createDummyEvent(user: User, category: Category, name: String) : Event {
        val event = Event(name = name,
                        type = this.createEventType(),
                        amount = Random.nextLong(100, 100000),
                        user = user,
                        category = category,
                        eventDate = this.createRandomEventDate(),
                        createdDate = LocalDateTime.now())
        this.eventRepository.save(event)
        return event
    }

    fun createEventType() : EventType {
        val num = Random.nextInt(3)
        return if(num == 1) EventType.OUTGOINGS else EventType.INCOME
    }

    fun createRandomEventDate() : LocalDate {
        val today = LocalDate.now()
        return today.minusMonths(Random.nextLong(3)).minusDays(Random.nextLong(1, 25))
    }
}