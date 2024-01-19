package kotlinassignment.week4and8.domain.exception

data class UnauthorizedAccessException(
    override val message: String = "해당 자원에 접근할 권한이 없습니다."
) : RuntimeException()
