package kotlinassignment.week4and8.domain.member.repository

import kotlinassignment.week4and8.domain.member.model.SocialMember
import kotlinassignment.week4and8.infra.client.oauth2.config.OAuth2Provider
import org.springframework.data.jpa.repository.JpaRepository

/* 챌린지반 강의 코드 가져와서 일부 수정 */
interface SocialMemberRepository : JpaRepository<SocialMember, Long> {

    fun existsByProviderAndIdFromProvider(oAuth2Provider: OAuth2Provider, idFromProvider: String): Boolean
    fun findByProviderAndIdFromProvider(oAuth2Provider: OAuth2Provider, idFromProvider: String): SocialMember
}
