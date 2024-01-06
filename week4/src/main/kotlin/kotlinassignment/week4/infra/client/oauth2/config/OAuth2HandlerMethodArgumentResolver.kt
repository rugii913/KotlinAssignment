package kotlinassignment.week4.infra.client.oauth2.config

import jakarta.servlet.http.HttpServletRequest
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import org.springframework.web.servlet.HandlerMapping

@Component
class OAuth2HandlerMethodArgumentResolver : HandlerMethodArgumentResolver {

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        // return parameter.parameterType.equals(OAuth2Provider::class)
        // OAuth2Provider::class는 Class 타입이 아니라 KClassImpl 타입이기 때문에 아래처럼 class.java로 작성해줘야 한다.
        return parameter.parameterType == OAuth2Provider::class.java
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any? {
        val pathVariableMap = webRequest.getNativeRequest(HttpServletRequest::class.java)
            ?.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE) as Map<*, *> // path variable 가져오기 - https://stackoverflow.com/questions/28938540/how-can-i-access-path-variables-in-my-custom-handlermethodargumentresolver
        val pathVariableValue = pathVariableMap[parameter.parameterName]

        val oAuth2Providers = OAuth2Provider.entries
        var target: OAuth2Provider? = null

        for (i in 0..<oAuth2Providers.size) {
            if (oAuth2Providers[i].uriSegment == pathVariableValue) {
                target = oAuth2Providers[i]
                break
            } else {
                throw IllegalArgumentException("요청 URI에 해당하는 OAuth2Provider를 찾을 수 없습니다.")
            }
        }

        return target
    }
}