package sixsense.scano.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import sixsense.scano.model.User
import sixsense.scano.repository.UserRepository

@Service
class UserService(
@Autowired val userRepository: UserRepository
) {
   fun getUserByContactNumber(contactNumber: String): User? {
       return userRepository.findByContactNumber(contactNumber)
   }
}