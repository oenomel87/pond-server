package dev.dane.pondserver.category.domain.entity

import dev.dane.pondserver.event.domain.entity.Event
import dev.dane.pondserver.user.domain.entity.User
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "CATEGORY")
class Category (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", columnDefinition = "BIGINT(20)")
    var id : Long? = null,

    @Column(name = "NAME", columnDefinition = "VARCHAR(20)", nullable = false)
    var name : String,

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    var user : User,

    @OneToMany(mappedBy = "category")
    var events : List<Event>,

    @CreationTimestamp
    @Column(name = "CREATED_DATE", updatable = false)
    var createdDate : LocalDateTime
)