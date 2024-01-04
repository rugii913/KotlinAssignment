package kotlinassignment.week4.domain.toDoCard.model

import jakarta.persistence.*
import kotlinassignment.week4.domain.comment.dto.CommentResponse
import kotlinassignment.week4.domain.comment.model.Comment
import kotlinassignment.week4.domain.comment.model.toResponse
import kotlinassignment.week4.domain.toDoCard.dto.ToDoCardResponse
import kotlinassignment.week4.domain.toDoCard.dto.ToDoCardResponseWithComments
import java.time.LocalDateTime

@Entity
class ToDoCard(
    @Column(nullable = false)
    var title: String,

    @Column(nullable = false)
    var description: String,

    @Column(nullable = false)
    val userName: String,

    @Column(nullable = false)
    val createdDateTime: LocalDateTime,

    @OneToMany(mappedBy = "toDoCard", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    val comments: MutableList<Comment> = mutableListOf(),
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(columnDefinition = "boolean default false") // https://m.blog.naver.com/younjh5369/222763814571 // https://www.baeldung.com/jpa-default-column-values
    var isComplete: Boolean = false
}

fun ToDoCard.toResponse(): ToDoCardResponse {
    return ToDoCardResponse(
        id = this.id!!,
        title = this.title,
        description = this.description,
        userName = this.userName,
        createdDateTime = this.createdDateTime,
        isComplete = this.isComplete,
    )
}

fun ToDoCard.toResponseWithComments(): ToDoCardResponseWithComments {
    /*
     ?? ToDoCard의 comments를 사용하지 않고, comment repository에서 페이징 처리한 comment를 가져오며 사용하지 않게 된 메서드
     ToDoCard가 참조하고 있는 comments를 사용하지 않고, comment repository 쪽에서 따로 Page 처리된 Comment들을 가져옴
     ToDoCard에서 직접 comments를 꺼내올 때도 paging 처리되게 하려면 어떻게 해야하는지 알아보기
     */
    return ToDoCardResponseWithComments(
        id = this.id!!,
        title = this.title,
        description = this.description,
        userName = this.userName,
        createdDateTime = this.createdDateTime,
        comments = this.comments.map { it.toResponse() }.sortedByDescending { it.createdDateTime },
        isComplete = this.isComplete,
    )
}

fun ToDoCard.toResponseWithComments(comments: List<CommentResponse>): ToDoCardResponseWithComments {
    return ToDoCardResponseWithComments(
        id = this.id!!,
        title = this.title,
        description = this.description,
        userName = this.userName,
        createdDateTime = this.createdDateTime,
        comments = comments,
        isComplete = this.isComplete,
    )
}

fun ToDoCard.addComment(comment: Comment) {
    this.comments.add(comment)
}
