package kotlinassignment.week4.domain.comment.dto

data class CommentUpdateRequest(
    var content: String,
    val password: String,
)
