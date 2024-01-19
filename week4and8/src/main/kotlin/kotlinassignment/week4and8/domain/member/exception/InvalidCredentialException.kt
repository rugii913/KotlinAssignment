package kotlinassignment.week4and8.domain.member.exception

class InvalidCredentialException(
    override val message: String? = "자격 증명이 필요합니다."
): RuntimeException()
