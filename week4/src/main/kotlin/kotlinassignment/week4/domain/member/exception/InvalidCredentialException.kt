package kotlinassignment.week4.domain.member.exception

class InvalidCredentialException(
    override val message: String? = "The credential is invalid"
): RuntimeException()
