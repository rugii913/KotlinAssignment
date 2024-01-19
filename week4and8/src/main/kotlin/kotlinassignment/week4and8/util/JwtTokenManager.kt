package kotlinassignment.week4and8.util

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.util.*
import kotlin.random.Random

/* 참고 ***https://velog.io/@max9106/OAuth4, https://velog.io/@blacklandbird/JWT%EB%A1%9C-TOKEN%EB%B0%9C%EA%B8%89%ED%95%98%EA%B8%B0, https://velog.io/@sophia5460/Spring-Boot-OAuth-2.0-JWT */
@Component
class JwtTokenManager { // TODO: 강의자료 참고하면서 리팩토링할 것 - 패키지 및 새로 작성할 코드와 겹치는 부분 정리하여 합치기

    val secretKey: String = "TODO()" // TODO

    fun createAccessToken(payload: String): String {
        return createToken(payload, ACCESS_TOKEN_EXPIRATION_LENGTH_IN_MILLISECONDS)
    }

    fun createRefreshToken(): String {
        val byteArray = byteArrayOf(0, 0, 0, 0, 0, 0, 0) // TODO 이 부분 어떻게 처리할지??
            .let { Random.nextBytes(it) }
        val generatedString = String(byteArray, StandardCharsets.UTF_8)
        return createToken(generatedString, REFRESH_TOKEN_EXPIRATION_LENGTH_IN_MILLISECONDS)
    }

    private fun createToken(payload: String, expirationLength: Int): String {
        val claims = Jwts.claims().subject(payload).build()
        val now = Date()
        val expirationTime = Date(now.time + expirationLength)
        val key = Keys.hmacShaKeyFor(secretKey.toByteArray(StandardCharsets.UTF_8))

        return Jwts.builder()
            .claims(claims)
            .issuedAt(now)
            .expiration(expirationTime)
            .signWith(key, Jwts.SIG.HS256)
            // signWith() 대부분 메서드 deprecated 참고 https://velog.io/@chodakk/Spring-Security-Kotlin-Jwts-signWith-deprecated-%EC%98%A4%EB%A5%98, https://stackoverflow.com/questions/73208128/whats-different-two-signwith-methods
            // SignatureAlgorithm deprecated - 정확한 대응방법은 못 찾겠음 - 일단 Jwts 중 타입 맞는 Jwts.SIG.HS256로 넣어두었음
            .compact();
    }
}

private const val ACCESS_TOKEN_EXPIRATION_LENGTH_IN_MILLISECONDS = 1_000 * 60 * 10
private const val REFRESH_TOKEN_EXPIRATION_LENGTH_IN_MILLISECONDS = 1_000 * 60 * 10
