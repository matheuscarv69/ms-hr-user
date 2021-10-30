package src.entities.user.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import src.entities.user.model.User

interface UserRepository : JpaRepository<User, Long> {

    fun findAllByActive(active: Boolean, pageable: Pageable): Page<User>

}