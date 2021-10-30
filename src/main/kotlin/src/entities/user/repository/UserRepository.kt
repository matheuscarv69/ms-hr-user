package src.entities.user.repository

import org.springframework.data.jpa.repository.JpaRepository
import src.entities.user.model.User

interface UserRepository : JpaRepository<User, Long>