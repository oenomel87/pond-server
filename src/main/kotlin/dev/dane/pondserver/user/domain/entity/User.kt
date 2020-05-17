package dev.dane.pondserver.user.domain.entity

import dev.dane.pondserver.category.domain.entity.Category
import dev.dane.pondserver.event.domain.entity.Event
import jdk.vm.ci.meta.Local
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "USER")
class User (username: String, password: String, name: String, userCode: String, createdDate: LocalDateTime) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", columnDefinition = "BIGINT(20)")
    var id: Long? = null

    @Column(name = "USERNAME", columnDefinition = "VARCHAR(50)", unique = true, nullable = false)
    var username: String = username

    @Column(name = "PASSWORD", columnDefinition = "VARCHAR(512)", nullable = false)
    var password: String = password

    @Column(name = "NAME", columnDefinition = "VARCHAR(20)", nullable = false)
    var name: String = name

    @Column(name = "USERCODE", columnDefinition = "VARCHAR(100)", unique = true, nullable = false)
    var userCode: String = userCode

    @CreationTimestamp
    @Column(name = "CREATED_DATE", updatable = false)
    var createdDate: LocalDateTime = createdDate

    @OneToMany(mappedBy = "user")
    var events: List<Event>? = null

    @OneToMany(mappedBy = "user")
    var categories: List<Category>? = null
}