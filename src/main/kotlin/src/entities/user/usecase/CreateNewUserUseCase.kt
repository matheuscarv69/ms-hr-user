package src.entities.user.usecase

import src.entities.user.model.User

interface CreateNewUserUseCase {

    fun createUser(userDomain: User): User

}