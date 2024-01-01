package kotlinassignment.week4.domain.comment.model

import jakarta.persistence.*
import kotlinassignment.week4.domain.comment.dto.CommentCreateRequest
import kotlinassignment.week4.domain.comment.dto.CommentResponse
import kotlinassignment.week4.domain.comment.dto.CommentUpdateRequest
import kotlinassignment.week4.domain.toDoCard.model.ToDoCard
import java.time.LocalDateTime

@Entity
class Comment(
    @Column(nullable = false)
    var content: String,

    @Column(nullable = false)
    val userName: String,

    @Column(nullable = false)
    val password: String,

    @Column(nullable = false)
    val createdDateTime: LocalDateTime,

    @ManyToOne
    @JoinColumn(name = "to_do_card_id", nullable = false)
    val toDoCard: ToDoCard,
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}

fun Comment.toResponse(): CommentResponse {
    return CommentResponse(
        id = this.id!!,
        content = this.content,
        userName = this.userName,
        createdDateTime = this.createdDateTime,
        toDoCardId = this.toDoCard.id!!,
    )
}

fun fromRequestToComment(request: CommentCreateRequest, targetToDoCard: ToDoCard): Comment {
    return Comment(
        content = request.content,
        userName = request.userName,
        password = request.password,
        createdDateTime = request.createdDateTime,
        toDoCard = targetToDoCard,
    )
}

fun Comment.updateFrom(request: CommentUpdateRequest) {
    this.content = request.content
}
