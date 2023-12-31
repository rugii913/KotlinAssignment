package kotlinassignment.week4.domain.comment.dto

import java.time.LocalDateTime

data class CommentCreateRequest(
    var content: String,
    val userName: String,
    val password: String,
    val createdDateTime: LocalDateTime,
)
