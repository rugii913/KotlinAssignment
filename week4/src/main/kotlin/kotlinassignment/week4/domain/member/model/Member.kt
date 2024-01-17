package kotlinassignment.week4.domain.member.model

import jakarta.persistence.*

@Entity
@Table(name = "member")
class Member(
    val email: String,
    val password: String,
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}
