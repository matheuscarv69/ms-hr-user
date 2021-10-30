package src.entities.user.request

import io.swagger.annotations.ApiModelProperty
import src.configs.validation.UniqueValue
import src.entities.user.model.User
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class NewUserRequest(

    @ApiModelProperty(value = "Name", position = 1, required = true)
    @field:NotBlank
    @field:Size(max = 100)
    val name: String,

    @ApiModelProperty(value = "Email", position = 2, required = true)
    @field:NotBlank
    @field:Size(max = 100)
    @field:Email
    @field:UniqueValue(
        domainClass = User::class,
        fieldName = "email",
        message = "{user.email.already.exists}"
    )
    val email: String,

    @ApiModelProperty(value = "Password", position = 3, required = true)
    @field:NotBlank
    @field:Size(max = 100)
    val password: String
) {

    fun toModel() = User(
        name = this.name,
        email = this.email,
        password = this.password
    )

}
