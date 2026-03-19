package sixsense.scano.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import sixsense.scano.model.Otp
import sixsense.scano.service.UserService
import java.security.SecureRandom
import java.time.Instant
import java.util.Date


@Repository
interface OtpRepository: JpaRepository<Otp, Long> {
    fun getOtpByUserContactNumber(contactNumber: String): Otp?

}
