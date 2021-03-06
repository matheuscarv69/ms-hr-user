package src.entities.user.request

import io.swagger.annotations.ApiModelProperty
import src.entities.user.model.User
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

class UpdateUserRequest(

    @ApiModelProperty(value = "Name", position = 1, required = true)
    @field:NotBlank
    @field:Size(max = 100)
    val name: String,

    @ApiModelProperty(value = "Email", position = 2, required = true)
    @field:NotBlank
    @field:Size(max = 100)
    @field:Email
    val email: String,

    @ApiModelProperty(value = "Password", position = 3, required = true)
    @field:NotBlank
    @field:Size(max = 100)
    val password: String,

    @ApiModelProperty(value = "Department", position = 4, required = true)
    @field:NotBlank
    @field:Size(max = 100)
    val department: String

) {

    fun toModel(userId: Long) = User(
        id = userId,
        name = this.name,
        email = this.email,
        password = this.password
    )

}
