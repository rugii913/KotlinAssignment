package com.teamsparta.courseregistration.infra.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

// JWT 인증이 아니라 다른 인증에서 사용할 수도 있으므로 jwt 패키지가 아닌 security 패키지에 두었다.

data class UserPrincipal(
    val id: Long,
    val email: String,
    val authorities: Collection<GrantedAuthority>
    // 나중에 확장성을 위해 Collection 형태로 정의함
    //  - Authentication 구현을 위해 JwtAuthenticationToken에서 AbstractAuthenticationToken을 상속해서 사용할 때,
    //  - AbstractAuthenticationToken 생성자가 Collection<? extends GrantedAuthority>를 받는 이유도 있는 듯
) {
    // 보조 생성자 사용 - role을 Set<String>으로 받고 Collection<GrantedAuthority로 바꿔주는 작업>
    constructor(id: Long, email: String, roles: Set<String>): this(
        id,
        email,
        roles.map { SimpleGrantedAuthority("ROLE_${it}") }
        // 보통 role를 넣을 때는 간단하게 SimpleGrantedAuthority 사용 - 그리고 그 앞에 "ROLE_"을 붙여주는 게 좋다.
        //  - 이유는 Security에서 GrantedAuthority 기반으로 권한 체크할 때 ROLE_{역할 이름}과 같은 방식으로 비교하므로 그 규칙을 미리 따라준 것
    )
}
