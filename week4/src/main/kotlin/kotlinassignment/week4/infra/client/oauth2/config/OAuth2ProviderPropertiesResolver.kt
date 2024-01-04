package kotlinassignment.week4.infra.client.oauth2.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("oauth2")
class OAuth2ProviderPropertiesResolver(
    val providerPropertiesMap: MutableMap<OAuth2Provider, OAuth2Properties>
) {

    fun getOAuth2Properties(oAuth2ProviderName: String): OAuth2Properties {
        if (oAuth2ProviderName !in OAuth2Provider.entries.map { it.uriPostfix }) {
            throw IllegalArgumentException("요청 URI에 해당하는 OAuth2Provider를 찾을 수 없습니다.")
        }

        return OAuth2Provider.valueOf(oAuth2ProviderName.uppercase())
            .let { this.providerPropertiesMap[it] } ?: throw IllegalArgumentException("요청 URI에 해당하는 OAuth2Provider를 찾을 수 없습니다.")
    }
}
