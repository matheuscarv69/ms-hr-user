package src.entities.user.usecase

interface GetUserUseCase {

    fun getUserById(userId: Long): src.entities.user.model.User

}
