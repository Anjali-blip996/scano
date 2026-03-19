package sixsense.scano.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig(

        ){
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .cors {  }
            .csrf { csrf -> csrf.disable() }
            .authorizeHttpRequests { authorizeRequests ->
                authorizeRequests.requestMatchers("/actuator/**").permitAll()
                authorizeRequests.requestMatchers("/v3/api-docs/**").permitAll()
                authorizeRequests.requestMatchers("/swagger-ui.html").permitAll()
                authorizeRequests.requestMatchers("/swagger-ui/**").permitAll()
                authorizeRequests.requestMatchers("/api/v2/authentication/**").permitAll()
                authorizeRequests.requestMatchers("/error**").permitAll()
                authorizeRequests.requestMatchers("/api/v1/payments/webhook").permitAll()
                authorizeRequests.anyRequest().authenticated()
            }
            .sessionManagement { sessionManagement ->
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
//            .addFilterBefore(
//                jwtAuthenticationFilter,
//                UsernamePasswordAuthenticationFilter::class.java
//            ).addFilterBefore(
//                itlTokenAuthenticationFilter,
//                JwtAuthenticationFilter::class.java
//            )

        return http.build()
    }
}