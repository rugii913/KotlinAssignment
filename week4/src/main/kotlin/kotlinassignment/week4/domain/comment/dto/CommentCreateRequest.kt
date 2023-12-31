package kotlinassignment.week4.domain.comment.dto

import java.time.LocalDateTime

data class CommentCreateRequest(
    val content: String,
    val userName: String,
    val password: String,
    val createdDateTime: LocalDateTime,
)
