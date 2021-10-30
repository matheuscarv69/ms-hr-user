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
import src.entities.user.request.UpdateUserRequest
import src.entities.user.response.DetailUserResponse
import src.entities.user.usecase.CreateNewUserUseCase
import src.entities.user.usecase.GetUserUseCase
import src.entities.user.usecase.UpdateUserUseCase
import javax.validation.Valid

@Api(tags = ["User"])
@RestController
@RequestMapping("/v1/users")
class UserController(
    @Autowired
    private val createNewUserService: CreateNewUserUseCase,

    @Autowired
    private val updateUserService: UpdateUserUseCase,

    @Autowired
    private val getUserService: GetUserUseCase
) {
    private val log = LoggerFactory.getLogger(this.javaClass)

    @ApiOperation("Register New User")
    @ApiResponses(
        value = [
            ApiResponse(code = 201, message = "Successfully registered user"),
            ApiResponse(code = 400, message = "Poorly Formatted Request"),
            ApiResponse(code = 500, message = "Internal Server Error")
        ]
    )
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

    @ApiOperation("Update User")
    @ApiResponses(
        value = [
            ApiResponse(code = 204, message = "User Updated successfully"),
            ApiResponse(code = 400, message = "Poorly Formatted Request"),
            ApiResponse(code = 404, message = "User Not Found"),
            ApiResponse(code = 500, message = "Internal Server Error")
        ]
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{userId}")
    fun updateUser(
        @PathVariable userId: Long,
        @RequestBody @Valid request: UpdateUserRequest
    ): ResponseEntity<Void> {
        log.info("Receiving request for user update, name: ${request.name}")

        updateUserService.updateUser(request.toModel(userId))

        return ResponseEntity.noContent().build()
    }

    @ApiOperation("Get User by ID")
    @ApiResponses(
        value = [
            ApiResponse(code = 200, message = "User found successfully"),
            ApiResponse(code = 404, message = "User Not Found"),
            ApiResponse(code = 500, message = "Internal Server Error")
        ]
    )
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{userId}")
    fun getUserById(
        @PathVariable userId: Long,
    ): ResponseEntity<DetailUserResponse> {
        log.info("Receiving request for found user, id: $userId")

        val userResponse = DetailUserResponse(getUserService.getUserById(userId))

        return ResponseEntity.ok(userResponse)
    }

    @ApiOperation("Enable User")
    @ApiResponses(
        value = [
            ApiResponse(code = 204, message = "User Enable successfully"),
            ApiResponse(code = 404, message = "User Not Found"),
            ApiResponse(code = 500, message = "Internal Server Error")
        ]
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{userId}/enable")
    fun enableUser(
        @PathVariable userId: Long,
    ): ResponseEntity<Void> {
        log.info("Receiving request for enable user, ID: $userId")

        updateUserService.enableUser(userId)

        return ResponseEntity.noContent().build()
    }

    @ApiOperation("Disable User")
    @ApiResponses(
        value = [
            ApiResponse(code = 204, message = "User Disable successfully"),
            ApiResponse(code = 404, message = "User Not Found"),
            ApiResponse(code = 500, message = "Internal Server Error")
        ]
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{userId}/disable")
    fun disableUser(
        @PathVariable userId: Long,
    ): ResponseEntity<Void> {
        log.info("Receiving request for disable user, ID: $userId")

        updateUserService.disableUser(userId)

        return ResponseEntity.noContent().build()
    }

}