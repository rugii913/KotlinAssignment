package com.teamsparta.courseregistration.infra.security.jwt

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(private val jwtPlugin: JwtPlugin): OncePerRequestFilter() {

    companion object {
        // Authorization Header로부터 JWT를 획득하기 위한 정규식
        private val BEARER_PATTERN = Regex("^Bearer (.+?)$")
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val jwt = request.getBearerToken()

        if (jwt != null) { // 추출이 잘 돼서 null이 아닐 때 인증 절차
            jwtPlugin.validateToken(jwt)
                .onSuccess { // 성공한 경우 Authentication 객체에 저장
                    // JWT로부터 정보 획득
                    val userId = it.payload.subject.toLong()
                    val role = it.payload.get("role", String::class.java)
                    val email = it.payload.get("email", String::class.java)
                    
                    // TODO: Authentication 구현체 만들어서 SecurityContext에 저장
                    //  - 이 때 principal에는 식별 정보들 넣을 것, credentials는 현재 민감 정보를 담는 용도로 사용하고 있지 않으므로 아무 것도 담지 않을 것
                }
        }

        // FilterChain 계속 진행
        filterChain.doFilter(request, response)
    }

    private fun HttpServletRequest.getBearerToken(): String? {
        return this.getHeader(HttpHeaders.AUTHORIZATION)
            ?.let { BEARER_PATTERN.find(it)?.groupValues?.get(1) }
    }
}
