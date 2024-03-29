package com.teamsparta.courseregistration.infra.security.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.time.Duration
import java.time.Instant
import java.util.*

@Component
class JwtPlugin(
    @Value("\${auth.jwt.issuer}") private val issuer: String,
    @Value("\${auth.jwt.secret}") private val secret: String,
    @Value("\${auth.jwt.accessTokenExpirationHour}") private val accessTokenExpirationHour: Long, // application.yml에 있는 property를 @Value로 가져옴
) { // JwtProvider, JwtUtil 등의 네이밍도 가능

    fun validateToken(jwt: String): Result<Jws<Claims>> { // try catch 사용하는 대신 Result을 사용(Kotlin에서 예외를 처리하는 한 방법 - 메서드 호출한 쪽에서 exception handling 할 수 있게 함)
        return kotlin.runCatching {
            val key = Keys.hmacShaKeyFor(secret.toByteArray(StandardCharsets.UTF_8))
            Jwts.parser().verifyWith(key).build().parseSignedClaims(jwt) // 위 key로 서명을 검증하고, 만료시간을 체크
        }
        // 더 응용해서 사용하고 싶은 경우 공식 문서 및 예시를 더 찾아볼 것
    }

    fun generateAccessToken(subject: String, email: String, role: String): String {
        return generateToken(subject, email, role, Duration.ofHours(accessTokenExpirationHour))
    }

    // refresh token 메서드 만들 때를 생각해서 메서드 분리
    private fun generateToken(subject: String, email: String, role: String, expirationPeriod: Duration): String {
        // custom claim 만들기
        val claims: Claims = Jwts.claims() // ClaimBuilder 사용
            .add(mapOf("role" to role, "email" to email))
            .build()

        val key = Keys.hmacShaKeyFor(secret.toByteArray(StandardCharsets.UTF_8))
        val now = Instant.now()

        // registered claim은 이미 builder에 작성이 되어 있다.
        return Jwts.builder()
            .subject(subject)
            .issuer(issuer)
            .issuedAt(Date.from(now))
            .expiration(Date.from(now.plus(expirationPeriod)))
            .claims(claims)
            .signWith(key)
            .compact()
    }
}