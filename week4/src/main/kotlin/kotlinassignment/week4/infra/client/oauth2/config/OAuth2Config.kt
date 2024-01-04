package kotlinassignment.week4.infra.client.oauth2.config

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(OAuth2ProviderPropertiesResolver::class)
class OAuth2Config(
    val oAuth2ProviderPropertiesResolver: OAuth2ProviderPropertiesResolver
)
