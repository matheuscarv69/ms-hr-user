package src.entities.user.usecase

import src.entities.user.model.User

interface UpdateUserUseCase {

    fun updateUser(userDomain: User)

    fun enableUser(userId: Long)

    fun disableUser(userId: Long)

}
