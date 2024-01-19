package kotlinassignment.week4and8.domain.member.model

import jakarta.persistence.*
import kotlinassignment.week4and8.infra.client.oauth2.config.OAuth2Provider

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
