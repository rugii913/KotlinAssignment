package kotlinassignment.week4.domain.toDoCard.dto

import kotlinassignment.week4.domain.comment.dto.CommentResponse
import java.time.LocalDateTime

data class ToDoCardResponseWithComments(
    val id: Long,
    val title: String,
    val description: String?,
    val userName: String,
    val createdDateTime: LocalDateTime,
    // val comments: List<Comment>, // entity 그대로 사용하면 순환 참조 https://dev-coco.tistory.com/133
    val comments: List<CommentResponse>,
)
