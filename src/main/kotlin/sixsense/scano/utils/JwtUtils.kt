package sixsense.scano.utils

import io.jsonwebtoken.Claims
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*

@Component
class JwtUtils(
    @Value("\${jwt.secret}") private val jwtSecret: String,
    @Value("\${jwt.expiration}") private val expirationThreshold: String
) {

    fun generateToken(phoneNumber: String): String? {

        val secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret))

        return Jwts.builder()
            .subject(phoneNumber)
            .issuedAt(Date.from(Instant.now()))
            .expiration(Date.from(Instant.now().plus(expirationThreshold.toLong(), ChronoUnit.MILLIS)))
            .signWith(secretKey)
            .compact()
    }
}
