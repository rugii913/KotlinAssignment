package com.teamsparta.courseregistration.infra.swagger

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class SwaggerConfig {

    @Bean
    fun openAPI(): OpenAPI = OpenAPI()
        // Swagger 자동 문서화 관련 Bean을 재정의, title, description, version 설정
        // 참고 https://springdoc.org/#how-can-i-set-a-global-header
        .components(Components())
        .info(
            Info()
                .title("Course API")
                .description("Course API schema")
                .version("1.0.0")
        )
}