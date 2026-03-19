package sixsense.scano.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.Instant
import java.util.Date

@Entity
@Table(name = "consumer_user")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "consumer_user_generator")
    @SequenceGenerator(name = "consumer_user_generator", sequenceName = "consumer_user_seq", allocationSize = 1)
    var id: Long = 0,

    @CreatedDate
    @Column(name = "created", nullable = false, updatable = false)
    var created: Date? = Date.from(Instant.now()),

    @LastModifiedDate
    @Column(name = "modified", nullable = false)
    var modified: Date? = Date.from(Instant.now()),

    @Column
    var firstName: String? = null,

    @Column
    var lastName: String? = null,

    @Column
    var contactNumber: String? = null,

    @Column
    var email: String? = null,

    @Column
    var dob: String? = null,

    @Column
    var bloodGroup: String? = null,

    @Column
    var address: String? = null,

//    @ManyToOne
//    @JoinColumn(name = "city_id")
//    var city: City? = null,

//    @ManyToOne
//    @JoinColumn(name = "state_id")
//    var state: State? = null,

    @Column
    var pinCode: String? = null,

    @Column
    var emergencyContactName: String? = null,

    @Column
    var emergencyContactPhone: String? = null,

    @Column
    var photo: String? = null,

    @Column
    var license: String? = null,

    @Column
    var panCard: String? = null,

    @Column(columnDefinition = "TEXT") // Use TEXT for large JSON strings
    var adhaarCard: String = "[]",

    var adhaarNumber: String? = null,

    @Column(length = 16)
    var licenseNumber: String? = null,

//    @ManyToOne
//    @JoinColumn(name = "organization_id")
//    var organization: Organization? = null,
//
//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
//    var status: DriverStatus = DriverStatus.AVAILABLE

    )