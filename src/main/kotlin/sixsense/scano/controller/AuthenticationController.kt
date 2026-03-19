package sixsense.scano.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import sixsense.scano.service.OtpService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import sixsense.scano.dto.GenerateOtpDto
import org.springframework.http.HttpStatus
import sixsense.scano.dto.TokenResponseDto
import sixsense.scano.dto.VerifyOtpDto
import sixsense.scano.model.Otp



//Code for testing
@RestController
@RequestMapping("/api/v2/authentication")
class AuthenticationController(
    @Autowired private val otpService: OtpService,
){
    @PostMapping("/generate-otp")
    fun generateOtp(@RequestBody generateOtpDto: GenerateOtpDto): ResponseEntity<HttpStatus>{
var result = otpService.generateOtp(generateOtpDto.contactNumber)

        return ResponseEntity(HttpStatus.CREATED)
    }
    @PostMapping("/verify-otp")
    fun verifyOtp(@RequestBody verifyOtpDto: VerifyOtpDto): ResponseEntity<TokenResponseDto> {
  val result = otpService.verifyOtp(verifyOtpDto.otp, verifyOtpDto.contactNumber)
       return ResponseEntity(result, HttpStatus.OK)
    }



}