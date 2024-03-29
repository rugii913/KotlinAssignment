package kotlinassignment.week4and8.domain.exception

data class StringLengthOutOfRangeException(val dataName: String, val minLength: Long, val maxLength: Long) : RuntimeException(
    "${dataName}의 길이 허용 범위는 $minLength 이상, $maxLength 이하입니다."
)
