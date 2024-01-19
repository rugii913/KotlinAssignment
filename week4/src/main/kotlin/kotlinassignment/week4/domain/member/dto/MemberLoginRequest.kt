package kotlinassignment.week4.domain.member.dto

data class MemberLoginRequest(
    val email: String,
    val password: String,
)
