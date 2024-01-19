package kotlinassignment.week4and8.domain.member.service

import kotlinassignment.week4and8.domain.member.dto.OAuth2MemberLoginResponse
import kotlinassignment.week4and8.infra.client.oauth2.OAuth2Client
import kotlinassignment.week4and8.infra.client.oauth2.config.OAuth2Provider
import kotlinassignment.week4and8.util.JwtTokenManager
import org.springframework.stereotype.Service

/* 챌린지반 강의 코드 가져와서 수정 + https://velog.io/@max9106/OAuth4 참고 */
@Service
class OAuth2LoginService(
    private val oAuth2Client: OAuth2Client,
    private val socialMemberService: SocialMemberService,
    private val jwtTokenManager: JwtTokenManager,
) {

    fun login(provider: OAuth2Provider, authorizationCode: String): OAuth2MemberLoginResponse {
        return oAuth2Client.getAccessToken(provider, authorizationCode)
            .let { oAuth2Client.retrieveUserInfo(provider, accessToken = it) }
            .let { socialMemberService.registerIfAbsent(provider, userInfoResponse = it) }
            .let {
                OAuth2MemberLoginResponse(
                    id = it.id!!,
                    nickname = it.nickname,
                    tokenType = "TODO",
                    accessToken = jwtTokenManager.createAccessToken(it.idFromProvider),
                    refreshToken = jwtTokenManager.createRefreshToken()
                )
            }
    }
}
