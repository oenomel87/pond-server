package dev.dane.pondserver.user.domain.entity

import dev.dane.pondserver.category.domain.entity.Category
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "USER")
class User (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", columnDefinition = "BIGINT(20)")
    var id : Long? = null,

    @Column(name = "USERNAME", columnDefinition = "VARCHAR(50)", unique = true, nullable = false)
    var username : String,

    @Column(name = "PASSWORD", columnDefinition = "VARCHAR(512)", nullable = false)
    var password : String,

    @Column(name = "NAME", columnDefinition = "VARCHAR(20)", nullable = false)
    var name : String,

    @Column(name = "USERCODE", columnDefinition = "VARCHAR(100)", unique = true, nullable = false)
    var userCode : String,

    @CreationTimestamp
    @Column(name = "CREATED_DATE", updatable = false)
    var createdDate : LocalDateTime,

    @OneToMany(mappedBy = "user")
    var categories : List<Category>
)