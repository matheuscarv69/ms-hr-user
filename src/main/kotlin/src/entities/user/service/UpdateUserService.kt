package src.entities.user.service

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import src.configs.exception.UserNotFoundException
import src.entities.user.model.User
import src.entities.user.repository.UserRepository
import src.entities.user.usecase.UpdateUserUseCase
import javax.transaction.Transactional

@Service
class UpdateUserService(

    @Autowired
    private val repository: UserRepository

) : UpdateUserUseCase {

    private val log = LoggerFactory.getLogger(this.javaClass)

    @Transactional
    override fun updateUser(userDomain: User) {
        log.info("Updating User: ${userDomain.name}")

        repository.findById(userDomain.id!!).orElseThrow {
            UserNotFoundException("This User ID: ${userDomain.id} not found.")
        }.let { user ->
            repository.save(userDomain)
            log.info("User Updated, name: ${user.name}")
        }

    }

    @Transactional
    override fun disableUser(userId: Long) {
        log.info("Deactivating User: $userId")

        repository.findById(userId).orElseThrow {
            UserNotFoundException("This User ID: $userId not found.")
        }.let { user ->
            user.disableUser()
            repository.save(user)
        }
    }

    @Transactional
    override fun enableUser(userId: Long) {
        log.info("Activating User: $userId")

        repository.findById(userId).orElseThrow {
            UserNotFoundException("This User ID: $userId not found.")
        }.let { user ->
            user.enableUser()
            repository.save(user)
        }
    }

}