package src.entities.user.controller

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import src.entities.user.request.NewUserRequest
import src.entities.user.usecase.CreateNewUserUseCase
import javax.validation.Valid

@Api(tags = ["User"])
@RestController
@RequestMapping("/v1/users")
class UserController(
    @Autowired
    private val createNewUserService: CreateNewUserUseCase
) {
    private val log = LoggerFactory.getLogger(this.javaClass)

    @ApiOperation("Register New User")
    @ApiResponses(value = [
        ApiResponse(code = 201, message = "Successfully registered user"),
        ApiResponse(code = 400, message = "Poorly Formatted Request"),
        ApiResponse(code = 500, message = "Internal Server Error")
    ])
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun createUser(
        @RequestBody @Valid request: NewUserRequest,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<Void> {
        log.info("Receiving request for user register, name: ${request.name}")

        val user = createNewUserService.createUser(request.toModel())

        val uri = uriBuilder.path("/v1/users/{userId}").buildAndExpand(user.id).toUri()
        return ResponseEntity.created(uri).build()
    }


}