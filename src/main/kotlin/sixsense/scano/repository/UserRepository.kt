package sixsense.scano.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import sixsense.scano.model.User
import sixsense.scano.service.UserService

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByContactNumber(userNumber: String): User?
}