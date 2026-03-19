package sixsense.scano.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Table
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.SequenceGenerator
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.Date

@Entity
@Table(name = "otp")
data class Otp (
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "otp_generator")
    @SequenceGenerator(name = "otp_generator", sequenceName = "otp_seq", allocationSize = 1)
    var id: Long = 1,

    @CreatedDate
    @Column(nullable = false, updatable = false)
    var created: Date = Date.from(Instant.now()),

    @LastModifiedDate
    @Column(nullable = false)
    var modified: Date = Date.from(Instant.now()),

    @Column
    var otp: String? = "",

    @Column
    var expiration: Date = Date.from(Instant.now().plus(5, ChronoUnit.MINUTES)),

    @OneToOne
    @JoinColumn(name = "consumer_user_id")
    var user: User? = null
)