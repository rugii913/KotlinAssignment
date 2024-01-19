package com.teamsparta.courseregistration.infra.security.jwt

import com.teamsparta.courseregistration.infra.security.UserPrincipal
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.web.authentication.WebAuthenticationDetails

class JwtAuthenticationToken(
    private val principal: UserPrincipal,
    details: WebAuthenticationDetails, // 로깅을 위한 요청 정보 담기
): AbstractAuthenticationToken(principal.authorities) { // Authentication을 바로 사용하기보다는 조금 더 구현된 AbstractAuthenticationToken을 사용

    init {
        super.setAuthenticated(true) // 이 프로젝트에서는 단순히 필터에서 인증 확인되면 true로 만들어주면 된다. 복잡하게 안 만들기로 정했으므로.
        super.setDetails(details)
    }

    override fun getCredentials() = null

    override fun getPrincipal() = principal

    override fun isAuthenticated() = true
}
