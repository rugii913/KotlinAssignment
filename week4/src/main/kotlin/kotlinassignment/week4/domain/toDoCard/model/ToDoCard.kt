package kotlinassignment.week4.domain.toDoCard.model

import jakarta.persistence.*
import kotlinassignment.week4.domain.toDoCard.dto.ToDoCardResponse
import java.time.LocalDateTime

@Entity
class ToDoCard(
    @Column(nullable = false)
    var title: String,

    @Column
    var description: String? = null,

    @Column(nullable = false)
    val userName: String,

    @Column(nullable = false)
    val createdDateTime: LocalDateTime,
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}

fun ToDoCard.toResponse(): ToDoCardResponse {
    return ToDoCardResponse(
        id = this.id!!,
        title = this.title,
        description = this.description,
        userName = this.userName,
        createdDateTime = this.createdDateTime,
    )
}
