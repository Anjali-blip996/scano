package sixsense.scano.strategy
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import retrofit2.Call
import sixsense.scano.service.Message91Service
import sixsense.scano.config.Msg91RetrofitConfig
import sixsense.scano.dto.OTPMessageDto
import org.slf4j.LoggerFactory
@Component
class Message91Strategy(
    @Autowired private val message91Service: Message91Service,
    @Autowired private val messageMsg91RetrofitConfig: Msg91RetrofitConfig

):MessageStrategy {
    private val logger = LoggerFactory.getLogger(Message91Service::class.java)
    override fun sendMessage(otp: String, mobile: String) {
        try {
            val otpMessageDto = OTPMessageDto(
                mobile = "+91$mobile",
                otp = otp,
                templateId = messageMsg91RetrofitConfig.smsTemplateId
            )
            logger.info("[SEND MESSAGE]: Sending otp $otp to mobile $mobile")
            val call: Call<Map<String, Any>> = message91Service.setOtp(otpMessageDto)

            val response: Map<String, Any>? = call.execute().body()
            logger.info("[SEND MESSAGE]: Message sent successfully, response -> ${response.toString()}")
        } catch (e: Exception) {
            logger.error("[SEND MESSAGE]: Message sent unsuccessful, error -> $e")
        }
    }
}