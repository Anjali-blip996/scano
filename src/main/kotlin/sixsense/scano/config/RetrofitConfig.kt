package sixsense.scano.config

import okhttp3.OkHttpClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory


@Configuration
class RetrofitConfig(
    @Value("\${tysis.url}") private val baseUrl: String,
    @Value("\${tysis.client_id}") private val clientId: String,
    @Value("\${tysis.client_secret}") private val clientSecret: String,
) {

    @Bean
    fun tysisRetrofit(): Retrofit {
        val client = OkHttpClient.Builder().addInterceptor { chain ->
            val request = chain.request()
                .newBuilder()
                .addHeader("Client_ID", clientId)
                .addHeader("Client_Secret", clientSecret)
                .build()
            chain.proceed(request)
        }.build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(JacksonConverterFactory.create())
            .client(client)
            .build()
    }

}