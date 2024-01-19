package com.teamsparta.courseregistration.infra.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.teamsparta.courseregistration.domain.exception.dto.ErrorResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component

@Component
class CustomAuthenticationEntryPoint: AuthenticationEntryPoint {

    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException,
    ) {
        response.status = HttpServletResponse.SC_UNAUTHORIZED
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.characterEncoding = "UTF-8"

        // HttpServlet 기반으로 돌아가므로 컨트롤러를 이용할 수 없어서, 에러 리스폰스를 보낼 때 간편하게 보낼 수가 없다.
        // - jackson 라이브러리의 ObjectMapper 직접 사용
        ObjectMapper().writeValueAsString(ErrorResponse("JWT verification failed"))
            .let { response.writer.write(it) }
    }
}