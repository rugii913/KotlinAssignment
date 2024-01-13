package kotlinassignment.week4.domain.comment.dto

import java.time.LocalDateTime

data class CommentResponse(
    val id: Long,
    val content: String,
    val userName: String,
    val createdDateTime: LocalDateTime,
    val toDoCardId: Long,
)
