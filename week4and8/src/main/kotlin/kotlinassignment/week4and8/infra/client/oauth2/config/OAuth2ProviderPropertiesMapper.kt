package kotlinassignment.week4and8.infra.client.oauth2.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("oauth2")
class OAuth2ProviderPropertiesMapper(
    private val providerPropertiesMap: MutableMap<OAuth2Provider, OAuth2Properties>
) {

    fun getOAuth2Properties(oAuth2Provider: OAuth2Provider): OAuth2Properties {
        return this.providerPropertiesMap[oAuth2Provider]
            ?: throw IllegalArgumentException("OAuth2Provider와 일치하는 OAuth2Properties를 찾을 수 없습니다.")
    }
}
