package kotlinassignment.week4.domain.member.model

import jakarta.persistence.*

/* 챌린지반 강의 코드 가져와서 일부 수정 */
@Entity
class SocialMember(
    @Enumerated(EnumType.STRING)
    val provider: OAuth2Provider,
    val idFromProvider: String,
    val nickname: String,
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}
