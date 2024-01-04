package kotlinassignment.week4.domain.member.controller

import jakarta.servlet.http.HttpServletResponse
import kotlinassignment.week4.infra.client.oauth2.OAuth2Client
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

/* 챌린지반 강의 코드 가져와서 수정 + https://velog.io/@max9106/OAuth4 참고 */
@RestController
class OAuth2MemberController(
    private val oAuth2Client: OAuth2Client,
) {
    @Value("\${oauth2.name}")
    val name: String? = null

    // 1. 로그인 페이지로 Redirect 해주는 API
    @GetMapping("/oauth2/login/{oAuth2ProviderName}")
    fun redirectLoginPage(
        @PathVariable oAuth2ProviderName: String,
        response: HttpServletResponse
    ) {
        val loginPageUrl = oAuth2Client.generateLoginPageUrl(oAuth2ProviderName)
        response.sendRedirect(loginPageUrl)
    }
}
