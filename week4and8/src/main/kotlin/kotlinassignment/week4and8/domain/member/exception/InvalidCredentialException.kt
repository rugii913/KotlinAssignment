package kotlinassignment.week4and8.domain.member.exception

class InvalidCredentialException(
    override val message: String? = "The credential is invalid"
): RuntimeException()
