package dev.dane.pondserver.event.domain.entity

import dev.dane.pondserver.category.domain.entity.Category
import dev.dane.pondserver.user.domain.entity.User
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "Event")
class Event (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", columnDefinition = "BIGINT(20)")
    var id : Long? = null,

    @Column(name = "NAME", columnDefinition = "VARCHAR(20)", nullable = false)
    var name : String,

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    var user : User,

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    var category : Category,

    @CreationTimestamp
    @Column(name = "CREATED_DATE", updatable = false)
    var createdDate : LocalDateTime
)