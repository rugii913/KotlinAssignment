package kotlinassignment.week4and8.infra.client.oauth2.config

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@EnableConfigurationProperties(OAuth2ProviderPropertiesMapper::class)
class OAuth2Config(
    private val oAuth2ProviderPropertiesMapper: OAuth2ProviderPropertiesMapper,
    private val oAuth2HandlerMethodArgumentResolver: OAuth2HandlerMethodArgumentResolver,
) : WebMvcConfigurer {

    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(oAuth2HandlerMethodArgumentResolver)
    }
}
