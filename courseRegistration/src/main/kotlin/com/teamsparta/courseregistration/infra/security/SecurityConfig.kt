package com.teamsparta.courseregistration.infra.security

import com.teamsparta.courseregistration.infra.security.jwt.JwtAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity // Http 기반 통신 시 보안 기능 관련
@EnableMethodSecurity
class SecurityConfig(
    private val jwtAuthenticationFilter: JwtAuthenticationFilter,
    private val authenticationEntryPoint: CustomAuthenticationEntryPoint,
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .httpBasic { it.disable() } // BasicAuthenticationFilter, DefaultLoginPageGeneratingFilter, DefaultLogoutPageGeneratingFilter 제외
            .formLogin { it.disable() } // UsernamePasswordAuthenticationFilter, DefaultLoginPageGeneratingFilter, DefaultLogoutPageGeneratingFilter 제외
            .csrf { it.disable() } // CsrfFilter 제외
            // 기본 필터들에서 위 필터들을 꺼준 것
            .authorizeHttpRequests {
                it.requestMatchers(
                    "/login",
                    "/signup",
                    "/swagger-ui/**",
                    "/v3/api-docs/**",
                ).permitAll() // 위에 명시한 URI를 제외하고는 모두 인증을 거치게 함
                    .anyRequest().authenticated()
            }
            // 기존 UsernamePasswordAuthenticationFilter가 존재하던 자리에 JwtAuthenticationFilter 적용
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
            .exceptionHandling {
                it.authenticationEntryPoint(authenticationEntryPoint)
            }
            .build()
        /*
        (???)
        그런데 이 부분 문법이 이해가 안 된다...
        httpBasic { it.disable() }
        
        풀어서 쓰면
        val function: (t: HttpBasicConfigurer<HttpSecurity>) -> Unit = { it.disable() }
        http.httpBasic(function)
        
        ==> 여기서 httpBasic의 매개변수인 org.springframework.security.config.Customizer는 함수형 인터페이스
        ====> 이 함수형 인터페이스가 갖고 있는 void customize(T t)를 바로 람다로 구현해준 것 - 람다이므로 it을 사용한 것임
        ==> 또한 Customizer의 제네릭 타입이 Customizer<HttpBasicConfigurer<HttpSecurity>>이므로
        ====> it은 T인 HttpBasicConfigurer 타입 객체를 가리키게 됨 
        ====> T인 HttpBasicConfigurer의 (정확하게는 그 상위 타입인 AbstractHttpConfigurer에서 구현된) disable()을 호출한 것
         */
    }
}
