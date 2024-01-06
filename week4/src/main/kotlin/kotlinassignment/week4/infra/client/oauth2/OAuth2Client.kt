package kotlinassignment.week4.infra.client.oauth2

import kotlinassignment.week4.infra.client.oauth2.config.OAuth2Properties
import kotlinassignment.week4.infra.client.oauth2.config.OAuth2Provider
import kotlinassignment.week4.infra.client.oauth2.config.OAuth2ProviderPropertiesMapper
import kotlinassignment.week4.infra.client.oauth2.dto.TokenResponse
import kotlinassignment.week4.infra.client.oauth2.dto.UserInfoResponse
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.client.RestClient
import org.springframework.web.client.body

/* 챌린지반 강의 코드 가져와서 수정 */
@Component
class OAuth2Client(
    private val restClient: RestClient,
    private val mapper: OAuth2ProviderPropertiesMapper,
) {

    fun generateLoginPageUrl(provider: OAuth2Provider): String {
        return getPropertiesCorrespondingWith(provider)
            .let {
                StringBuilder(it.authBaseUri)
                    .append("/oauth/authorize")
                    .append("?client_id=").append(it.clientId)
                    .append("&redirect_uri=").append(it.redirectUri)
                    .append("&response_type=").append("code")
                    .toString()
            }
    }

    fun getAccessToken(provider: OAuth2Provider, authorizationCode: String): String {
        val properties = getPropertiesCorrespondingWith(provider)

        val requestData = mutableMapOf(
            "grant_type" to "authorization_code",
            "client_id" to properties.clientId,
            "code" to authorizationCode
        )

        return restClient.post()
            .uri("${properties.authBaseUri}/oauth/token")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .body(LinkedMultiValueMap<String, String>().apply { this.setAll(requestData) })
            .retrieve()
            .body<TokenResponse>()
            ?.accessToken
            ?: throw RuntimeException("AccessToken 조회 실패")
    }

    fun retrieveUserInfo(provider: OAuth2Provider, accessToken: String): UserInfoResponse {
        return getPropertiesCorrespondingWith(provider)
            .let {
                restClient.get()
                    .uri("${it.apiBaseUri}/v2/user/me")
                    .header("Authorization", "Bearer $accessToken")
                    .retrieve()
                    .body<UserInfoResponse>()
                    ?: throw RuntimeException("UserInfo 조회 실패")
            }
    }

    private fun getPropertiesCorrespondingWith(provider: OAuth2Provider): OAuth2Properties {
        return mapper.getOAuth2Properties(provider)
    }
}
