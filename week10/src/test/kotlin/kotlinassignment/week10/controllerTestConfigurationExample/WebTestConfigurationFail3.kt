package kotlinassignment.week10.controllerTestConfigurationExample

import kotlinassignment.week10.infra.jwt.JwtUtil
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.test.context.TestPropertySource

@TestPropertySource(
    properties = [
        "authentication.member.jwt.issuer = test",
        "authentication.member.jwt.secret = ABCDEabcde1234567890ABCDEabcde12",
        "authentication.member.jwt.accessTokenExpirationHour = 1",
    ]
)
@TestConfiguration
class WebTestConfigurationFail3 (
    @Value("\${authentication.member.jwt.issuer}") private val issuer: String,
    @Value("\${authentication.member.jwt.secret}") private val secret: String,
    @Value("\${authentication.member.jwt.accessTokenExpirationHour}") private val accessTokenExpirationHour: Long,
) {

    @Bean
    fun jwtUtil(): JwtUtil {
        return JwtUtil(issuer, secret, accessTokenExpirationHour)
    }
}