package kotlinassignment.week4.domain.member.service

import kotlinassignment.week4.domain.member.dto.LoginResponse
import kotlinassignment.week4.infra.client.oauth2.OAuth2Client
import kotlinassignment.week4.infra.client.oauth2.config.OAuth2ProviderPropertiesResolver
import kotlinassignment.week4.util.JwtTokenManager
import org.springframework.stereotype.Service

/* 챌린지반 강의 코드 가져와서 수정 + https://velog.io/@max9106/OAuth4 참고 */
@Service
class OAuth2LoginService(
    private val oAuth2Client: OAuth2Client,
    private val resolver: OAuth2ProviderPropertiesResolver,
    private val socialMemberService: SocialMemberService,
    private val jwtTokenManager: JwtTokenManager,
) {

    fun login(oAuth2ProviderName: String, authorizationCode: String): LoginResponse {
        return oAuth2Client.getAccessToken(oAuth2ProviderName, authorizationCode)
            .let { oAuth2Client.retrieveUserInfo(oAuth2ProviderName, accessToken = it) }
            .let { socialMemberService.registerIfAbsent(resolver.getOAuth2Provider(oAuth2ProviderName), userInfoResponse = it) }
            .let {
                LoginResponse(
                    id = it.id!!,
                    nickname = it.nickname,
                    tokenType = "TODO",
                    accessToken = jwtTokenManager.createAccessToken(it.idFromProvider),
                    refreshToken = jwtTokenManager.createRefreshToken()
                )
            }
    }
}
