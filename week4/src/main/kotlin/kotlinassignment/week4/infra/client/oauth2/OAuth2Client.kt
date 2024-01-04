package kotlinassignment.week4.infra.client.oauth2

import kotlinassignment.week4.infra.client.oauth2.config.OAuth2ProviderPropertiesResolver
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
    private val resolver: OAuth2ProviderPropertiesResolver,
) {

    fun generateLoginPageUrl(oAuth2ProviderName: String): String {
        val properties = resolver.getOAuth2Properties(oAuth2ProviderName)

        val loginPageUrl = StringBuilder(properties.authBaseUri)
            .append("/oauth/authorize")
            .append("?client_id=").append(properties.clientId)
            .append("&redirect_uri=").append(properties.redirectUri)
            .append("&response_type=").append("code")
            .toString()

        return loginPageUrl
    }

    fun getAccessToken(oAuth2ProviderName: String, authorizationCode: String): String {
        val properties = resolver.getOAuth2Properties(oAuth2ProviderName)

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

    fun retrieveUserInfo(oAuth2ProviderName: String, accessToken: String): UserInfoResponse {
        val properties = resolver.getOAuth2Properties(oAuth2ProviderName)

        return restClient.get()
            .uri("${properties.apiBaseUri}/v2/user/me")
            .header("Authorization", "Bearer $accessToken")
            .retrieve()
            .body<UserInfoResponse>()
            ?: throw RuntimeException("UserInfo 조회 실패")
    }
}
