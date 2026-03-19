package sixsense.scano.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class OTPMessageDto (
    @JsonProperty("template_id")
    var templateId: String,
    var mobile: String,
    var otp: String,
)
