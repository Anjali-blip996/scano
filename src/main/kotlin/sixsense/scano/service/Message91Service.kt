package sixsense.scano.service
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import sixsense.scano.dto.OTPMessageDto
import sixsense.scano.model.Otp

interface Message91Service {

    @POST("otp")
    fun setOtp(@Body otpMessage: OTPMessageDto): Call<Map<String, Any>>






}