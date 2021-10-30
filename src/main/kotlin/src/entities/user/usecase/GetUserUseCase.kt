package src.entities.user.usecase

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import src.entities.user.model.User

interface GetUserUseCase {

    fun getUserById(userId: Long): User

    fun getAllUser(active: Boolean, pageable: Pageable): Page<User>

}
