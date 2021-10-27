package src.entities.user.model

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity(name = "user")
class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @field:NotBlank
    @field:Size(max = 100)
    @Column(nullable = false)
    val name: String,

    @field:NotBlank
    @field:Email
    @field:Size(max = 100)
    @Column(nullable = false)
    val email: String,

    @field:NotBlank
    @Column(nullable = false)
    val password: String,

    @field:NotBlank
    @field:JsonFormat(pattern = "dd/MM/aaaa", shape = JsonFormat.Shape.STRING)
    @Column(nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @field:NotNull
    @Column(nullable = false)
    var active: Boolean = true
) {

    fun enableUser(): Boolean {
        this.active = true
        return active
    }

    fun disableUser(): Boolean {
        this.active = false
        return active
    }
}