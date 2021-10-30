package src.entities.user.usecase

import src.entities.user.model.User

interface UpdateUserUseCase {

    fun updateUser(userDomain: User)

}
