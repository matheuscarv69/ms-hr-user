package src.entities.user.service

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import src.entities.user.model.User
import src.entities.user.repository.UserRepository
import src.entities.user.usecase.CreateNewUserUseCase
import javax.transaction.Transactional

@Service
class CreateNewUserService(
    @Autowired
    private val repository: UserRepository
) : CreateNewUserUseCase {

    private val log = LoggerFactory.getLogger(this.javaClass)

    @Transactional
    override fun createUser(userDomain: User): User {
        log.info("Registering new User: ${userDomain.name}")

        return repository.save(userDomain)
    }


}