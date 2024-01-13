package kotlinassignment.week4.domain.comment.dto

data class CommentUpdateRequest(
    val content: String,
    val userName: String,
    val password: String,
)
