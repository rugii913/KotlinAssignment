package kotlinassignment.week4.infra.swagger

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class SwaggerConfig {

    @Bean
    fun openAPI(): OpenAPI = OpenAPI()
        // Swagger 관련 Bean 재정의(title, description, version) - https://springdoc.org/#how-can-i-set-a-global-header
        // 확인 url: http://localhost:8080/swagger-ui/index.html#/
        .components(Components())
        .info(
            Info()
                .title("to do app API")
                .description("to do app API schema")
                .version("1.0.0")
        )
}