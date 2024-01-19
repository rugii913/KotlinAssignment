package com.teamsparta.courseregistration.infra.security.jwt

import com.teamsparta.courseregistration.infra.security.UserPrincipal
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
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

                    val principal = UserPrincipal(
                        id = userId,
                        email = email,
                        roles = setOf(role),
                    )

                    // 인증이 완료 됐으므로 Authentication 객체(AbstractAuthenticationToken 사용) 만들어서 context에 세팅할 것
                    // 일단 Authentication 구현체 생성
                    val authentication = JwtAuthenticationToken(
                        principal = principal,
                        details = WebAuthenticationDetailsSource().buildDetails(request), // request로부터 상세 정보들을 로깅용으로 넣어줌
                    )

                    // Authentication 객체를 SecurityContextHolder에 세팅 = SecurityContext에 저장하는 것 
                    SecurityContextHolder.getContext().authentication = authentication 
                }
                // .onFailure {  } 실무라면 이 부분까지 작성해야 맞다. failure 이유 - 만료, 이상한 JWT, ... 등 경우에 따른 오류 처리
        }

        // FilterChain 계속 진행 - filterChain.doFilter(~) 하면 알아서 다음 필터로 흐름이 넘어감
        filterChain.doFilter(request, response)
    }

    private fun HttpServletRequest.getBearerToken(): String? {
        return this.getHeader(HttpHeaders.AUTHORIZATION)
            ?.let { BEARER_PATTERN.find(it)?.groupValues?.get(1) }
    }
}
