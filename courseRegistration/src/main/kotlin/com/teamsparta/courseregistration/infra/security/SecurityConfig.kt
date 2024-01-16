package com.teamsparta.courseregistration.infra.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity // Http 기반 통신 시 보안 기능 관련
class SecurityConfig {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .httpBasic { it.disable() } // BasicAuthenticationFilter, DefaultLoginPageGeneratingFilter, DefaultLogoutPageGeneratingFilter 제외
            .formLogin { it.disable() } // UsernamePasswordAuthenticationFilter, DefaultLoginPageGeneratingFilter, DefaultLogoutPageGeneratingFilter 제외
            .csrf { it.disable() } // CsrfFilter 제외
            .build() // 기본 필터들에서 위 필터들을 꺼준 것
        /*
        그런데 이 부분 문법이 이해가 안 된다...
        val function: (t: HttpBasicConfigurer<HttpSecurity>) -> Unit = { it.disable() }
        http.httpBasic(function)
         */
    }
}
