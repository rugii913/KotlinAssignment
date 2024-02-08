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
class WebTestConfigurationFail1and2 {

    @Bean
    fun jwtUtil(
        @Value("\${authentication.member.jwt.issuer}") issuer: String,
        @Value("\${authentication.member.jwt.secret}") secret: String,
        @Value("\${authentication.member.jwt.accessTokenExpirationHour}") accessTokenExpirationHour: Long,
    ): JwtUtil {
        return JwtUtil(issuer, secret, accessTokenExpirationHour)
    }
}