package sixsense.scano.config

import sixsense.scano.service.Message91Service
import okhttp3.OkHttpClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

@Configuration
class Msg91RetrofitConfig(
    @Value("\${sixsense.messenger.url}") var baseUrl: String,
    @Value("\${sixsense.messenger.otpMessageTemplateId}") val smsTemplateId: String,
    @Value("\${sixsense.messenger.emailTemplateId}") val mailTemplateId: String,
    @Value("\${sixsense.messenger.domain}") val messageDomain: String,
    @Value("\${sixsense.messenger.email}") val messageEmail: String,
    @Value("\${sixsense.messenger.authKey}") val authKey: String
) {

    @Bean
    fun msg91Retrofit(): Retrofit {
        val objectMapper = jacksonObjectMapper()
        val okHttpClient = OkHttpClient.Builder().addInterceptor { chain ->
            val originalRequest = chain.request()
            val requestWithAuth = if (authKey.isNotBlank()) {
                originalRequest.newBuilder()
                    .addHeader("authkey", authKey)
                    .build()
            } else {
                originalRequest
            }
            chain.proceed(requestWithAuth)
        }.build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(JacksonConverterFactory.create(objectMapper))
            .client(okHttpClient)
            .build()
    }

    @Bean
    fun message91Service(msg91Retrofit: Retrofit): Message91Service {
        return msg91Retrofit.create(Message91Service::class.java)
    }
}