package sixsense.scano.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import sixsense.scano.model.Otp
import sixsense.scano.model.User
import sixsense.scano.repository.OtpRepository
import sixsense.scano.repository.UserRepository
import java.security.SecureRandom
import java.time.Instant
import java.util.Date
import sixsense.scano.dto.TokenResponseDto
import org.springframework.beans.factory.annotation.Autowired
import sixsense.scano.utils.JwtUtils
import sixsense.scano.exceptions.UnauthorizedException
import sixsense.scano.strategy.MessageStrategyNavigator

@Service
class OtpService(
    @Value("\${authentication.otp.threshold}") private val otpExpirationThreshold: String,

    @Autowired private val otpRepository: OtpRepository,
    @Autowired private val userService: UserService,
    @Autowired private val userRepository: UserRepository,
    @Autowired private val jwtUtils: JwtUtils,
    @Autowired private val  messageStrategyNavigator: MessageStrategyNavigator


    ) {

    private val random = SecureRandom()
    fun generateOtp(contactNumber: String){

        val user: User? = userService.getUserByContactNumber(contactNumber)
        if (user == null){
            val newUser = User(contactNumber = contactNumber);
            userRepository.save(newUser)
        }

        val generatedOtp = (100000 + random.nextInt(900000)).toString()
        val expirationTime = Date.from(Instant.now().plusMillis(otpExpirationThreshold.toLong()))


        var otp = otpRepository.getOtpByUserContactNumber(contactNumber)
        if(otp == null){
            otp = Otp(otp= generatedOtp, expiration = expirationTime,user = user)
        }
        else{
      otp.apply{
          this.otp = generatedOtp
          this.expiration = expirationTime
      }
        }
       otpRepository.save(otp)
        messageStrategyNavigator.sendMessage(generatedOtp, contactNumber)
    }


    fun verifyOtp(otp: String, contactNumber: String): TokenResponseDto{
        val fetchedOtp = otpRepository.getOtpByUserContactNumber(contactNumber)?: throw Exception("OTP for user $contactNumber not found")
        if(fetchedOtp.otp.equals(otp) && fetchedOtp.expiration > Date.from(Instant.now())) {
            val token: String = jwtUtils.generateToken(contactNumber)?: throw Exception("Error fetchin token for contact number $contactNumber")
            return TokenResponseDto(accessToken = token);
        }
        throw UnauthorizedException("Token Expired")
    }





}