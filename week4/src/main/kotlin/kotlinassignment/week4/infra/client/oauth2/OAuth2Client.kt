package kotlinassignment.week4.infra.client.oauth2

import kotlinassignment.week4.infra.client.oauth2.config.OAuth2ProviderPropertiesResolver
import org.springframework.stereotype.Component

/* 챌린지반 강의 코드 가져와서 일부 수정 */
@Component
class OAuth2Client(
    val resolver: OAuth2ProviderPropertiesResolver
) {

    fun generateLoginPageUrl(oAuth2ProviderName: String): String {
        val properties = resolver.getOAuth2Properties(oAuth2ProviderName)

        val loginPageUrl = StringBuilder(properties.baseUri)
            .append("/oauth/authorize")
            .append("?client_id=").append(properties.clientId)
            .append("&redirect_uri=").append(properties.redirectUri)
            .append("&response_type=").append("code")
            .toString()

        return loginPageUrl
    }
}
