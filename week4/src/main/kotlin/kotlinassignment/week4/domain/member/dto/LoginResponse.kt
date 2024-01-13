package kotlinassignment.week4.domain.member.dto

data class LoginResponse(
    val id: Long,
    val nickname: String,
    val tokenType: String,
    val accessToken: String?,
    val refreshToken: String?,
)
