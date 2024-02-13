package kotlinassignment.week10.infra.security

import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.boot.autoconfigure.web.reactive.WebFluxAutoConfiguration.WelcomePageConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher
import org.springframework.web.servlet.handler.HandlerMappingIntrospector

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtAuthenticationFilter: JwtAuthenticationFilter,
    private val authenticationEntryPoint: AuthenticationEntryPoint,
    private val accessDeniedHandler: AccessDeniedHandler,
) {

    @Bean
    fun filterChain(httpSecurity: HttpSecurity, introspector: HandlerMappingIntrospector): SecurityFilterChain {
        return httpSecurity
            .httpBasic { it.disable() }
            .formLogin { it.disable() }
            .csrf { it.disable() }
            .headers { it.frameOptions { frameOptionsConfig -> frameOptionsConfig.sameOrigin() } }
            .authorizeHttpRequests {
                it.requestMatchers(
                    "/login",
                    "/signup",
                    "/swagger-ui/**",
                    "/v3/api-docs/**",
                    "/h2-console/**",
                    "/images",
                ).permitAll()
                    .requestMatchers( // https://colabear754.tistory.com/170 - Spring Security 사용하면서 H2 콘솔 사용 - TODO 정확한 원리 알아보기
                        MvcRequestMatcher(introspector, "/**").apply { setServletPath("/h2-console") }
                    ).permitAll()
                    .requestMatchers(
                        // https://countryxide.tistory.com/14, https://ktae23.tistory.com/261, https://nahwasa.com/entry/%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8-30%EC%9D%B4%EC%83%81-Spring-Security-%EA%B8%B0%EB%B3%B8-%EC%84%B8%ED%8C%85-%EC%8A%A4%ED%94%84%EB%A7%81-%EC%8B%9C%ED%81%90%EB%A6%AC%ED%8B%B0, https://ktae23.tistory.com/261, https://spring.io/guides/gs/securing-web
                        // 공식 문서 (https://spring.io/guides/gs/securing-web)와 크게 다르지 않음
                        // 그럼에도 불구하고 계속 401 에러 발생했음, app.js 추가하자, 정상적으로 연결됨
                        "/",
                        "/index.html",
                        "/app.js",
                        "/static/**",
                    ).permitAll()
                    .requestMatchers(
                        PathRequest.toStaticResources().atCommonLocations()
                    ).permitAll()
                    .anyRequest().authenticated()
            }
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java) // TODO 예외 처리
            .exceptionHandling {
                it.authenticationEntryPoint(authenticationEntryPoint)
                // it.accessDeniedHandler(accessDeniedHandler)
            }
            .build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder() // 예시 자료와는 다르게 우선 같은 config에 넣어둠, 분리 필요할 경우 분리
}
