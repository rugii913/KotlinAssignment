package kotlinassignment.week4.domain.member.controller

import jakarta.servlet.http.HttpServletResponse
import kotlinassignment.week4.domain.member.dto.LoginResponse
import kotlinassignment.week4.domain.member.service.OAuth2LoginService
import kotlinassignment.week4.infra.client.oauth2.OAuth2Client
import kotlinassignment.week4.infra.client.oauth2.config.OAuth2ProviderPropertiesResolver
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.net.URI

/* 챌린지반 강의 코드 가져와서 수정 + https://velog.io/@max9106/OAuth4 참고 */
@RestController
class OAuth2MemberController(
    private val resolver: OAuth2ProviderPropertiesResolver,
    private val oAuth2LoginService: OAuth2LoginService,
    private val oAuth2Client: OAuth2Client,
) {

    // 1. 로그인 페이지로 Redirect 해주는 API
    @GetMapping("/oauth2/login/{oAuth2ProviderName}")
    fun redirectLoginPage(
        @PathVariable oAuth2ProviderName: String,
        response: HttpServletResponse,
    ): ResponseEntity<Unit> {
        val loginPageUrl = resolver.getOAuth2Properties(oAuth2ProviderName)
            .let { oAuth2Client.generateLoginPageUrl(it) }

        // response.sendRedirect(loginPageUrl) 왼쪽과 같은 HttpServletResponse가 아니라 ResponseEntity로도 redirect 가능 - https://shanepark.tistory.com/370
        val headers = HttpHeaders().apply { this.location = URI.create(loginPageUrl) }

        return ResponseEntity(headers, HttpStatus.SEE_OTHER) // 이 303 코드가 맞을까? https://developer.mozilla.org/ko/docs/Web/HTTP/Status/303
    }

    // 2. authorization code를 받아서 사용자 인증을 처리(access token 발급 요청, 사용자 정보 요청, access token 발행 등)
    @GetMapping("/oauth2/callback/{oAuth2ProviderName}") // redirect url
    fun callback(
        @PathVariable oAuth2ProviderName: String,
        @RequestParam(name = "code") authorizationCode: String,
    ): ResponseEntity<LoginResponse> {
        val properties = resolver.getOAuth2Properties(oAuth2ProviderName)
        val oAuth2Provider = resolver.getOAuth2Provider(oAuth2ProviderName)

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(oAuth2LoginService.login(properties, oAuth2Provider, authorizationCode))
    }
}
