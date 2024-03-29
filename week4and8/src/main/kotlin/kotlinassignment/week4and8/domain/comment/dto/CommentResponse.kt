package kotlinassignment.week4and8.domain.comment.dto

import java.time.LocalDateTime

data class CommentResponse(
    val id: Long,
    val content: String,
    val memberNickname: String,
    val createdDateTime: LocalDateTime,
    val toDoCardId: Long,
)
