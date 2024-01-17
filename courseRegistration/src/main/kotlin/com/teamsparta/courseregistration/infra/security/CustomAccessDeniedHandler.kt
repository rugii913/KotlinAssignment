package com.teamsparta.courseregistration.infra.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.teamsparta.courseregistration.domain.exception.dto.ErrorResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component

@Component
class CustomAccessDeniedHandler: AccessDeniedHandler {

    override fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        accessDeniedException: AccessDeniedException
    ) {
        response.status = HttpServletResponse.SC_FORBIDDEN
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.characterEncoding = "UTF-8"

        ObjectMapper().writeValueAsString(ErrorResponse("No permission to run API"))
            .let { response.writer.write(it) }
    }
}
