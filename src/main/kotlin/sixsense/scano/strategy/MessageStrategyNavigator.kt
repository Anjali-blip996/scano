package sixsense.scano.strategy
import org.springframework.stereotype.Component
import org.springframework.beans.factory.annotation.Autowired
import sixsense.scano.repository.OtpRepository

@Component

class MessageStrategyNavigator(
    @Autowired private val message91Strategy: Message91Strategy
) {

    fun sendMessage(otp: String,message: String) {
        message91Strategy.sendMessage(otp, message);
    }




}