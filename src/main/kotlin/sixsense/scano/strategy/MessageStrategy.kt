package sixsense.scano.strategy

interface MessageStrategy {
    fun sendMessage(otp: String, message: String)
}