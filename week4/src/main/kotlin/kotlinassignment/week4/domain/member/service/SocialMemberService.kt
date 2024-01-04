package kotlinassignment.week4.domain.member.service

import kotlinassignment.week4.domain.member.model.SocialMember
import kotlinassignment.week4.domain.member.repository.SocialMemberRepository
import kotlinassignment.week4.infra.client.oauth2.config.OAuth2Provider
import kotlinassignment.week4.infra.client.oauth2.dto.UserInfoResponse
import org.springframework.stereotype.Service

/* 챌린지반 강의 코드 가져와서 수정 + https://velog.io/@max9106/OAuth4 참고 */
@Service
class SocialMemberService(
    private val socialMemberRepository: SocialMemberRepository,
) {

    fun registerIfAbsent(oAuth2Provider: OAuth2Provider, userInfoResponse: UserInfoResponse): SocialMember {
        val idFromProvider = userInfoResponse.id.toString()

        return if (!socialMemberRepository.existsByProviderAndIdFromProvider(oAuth2Provider, idFromProvider)) {
            val socialMember = SocialMember(oAuth2Provider, idFromProvider, userInfoResponse.nickname)
            socialMemberRepository.save(socialMember)
        } else {
            socialMemberRepository.findByProviderAndIdFromProvider(oAuth2Provider, idFromProvider)
        }
    }
}