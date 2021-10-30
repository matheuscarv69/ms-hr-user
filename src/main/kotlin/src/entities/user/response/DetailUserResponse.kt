package src.entities.user.response

import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.annotations.ApiModelProperty
import src.entities.user.model.User
import java.time.LocalDateTime

class DetailUserResponse(
    @ApiModelProperty(value = "Name", position = 1)
    val name: String,

    @ApiModelProperty(value = "Email", position = 2)
    val email: String,

    @ApiModelProperty(value = "createdAt", position = 3)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    val createdAt: LocalDateTime,

    @ApiModelProperty(value = "active", position = 4)
    val active: Boolean
) {

    constructor(user: User) : this(
        name = user.name,
        email = user.email,
        createdAt = user.createdAt,
        active = user.active
    )

}
