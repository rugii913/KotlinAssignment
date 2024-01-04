package kotlinassignment.week4.domain.member.service

import kotlinassignment.week4.infra.client.oauth2.OAuth2Client
import kotlinassignment.week4.infra.client.oauth2.config.OAuth2ProviderPropertiesResolver
import org.springframework.stereotype.Service

/* 챌린지반 강의 코드 가져와서 수정 + https://velog.io/@max9106/OAuth4 참고 */
@Service
class OAuth2LoginService(
    private val oAuth2Client: OAuth2Client,
    private val resolver: OAuth2ProviderPropertiesResolver,
    private val socialMemberService: SocialMemberService,
) {

    fun login(oAuth2ProviderName: String, authorizationCode: String): String {
        return oAuth2Client.getAccessToken(oAuth2ProviderName, authorizationCode)
            .let { oAuth2Client.retrieveUserInfo(oAuth2ProviderName, accessToken = it) }
            .let { socialMemberService.registerIfAbsent(resolver.getOAuth2Provider(oAuth2ProviderName), userInfoResponse = it) }
            .let { TODO("위에서 찾은 SocialMember를 바탕으로 resource owner에게 access token을 발행해야 한다.") }
    }
}
