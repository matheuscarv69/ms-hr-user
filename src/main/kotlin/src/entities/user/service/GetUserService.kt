package src.entities.user.service

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import src.configs.exception.UserNotFoundException
import src.entities.user.model.User
import src.entities.user.repository.UserRepository
import src.entities.user.usecase.GetUserUseCase

@Service
class GetUserService(

    @Autowired
    private val repository: UserRepository

) : GetUserUseCase {

    private val log = LoggerFactory.getLogger(this.javaClass)

    override fun getUserById(userId: Long): User {
        log.info("Getting User by ID: $userId")

        return repository.findById(userId).orElseThrow {
            UserNotFoundException("This User ID: $userId not found.")
        }

    }

    override fun getAllUser(active: Boolean, pageable: Pageable): Page<User> {
        log.info("Getting all Users")

        return repository.findAllByActive(active, pageable)

    }


}