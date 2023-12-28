package kotlinassignment.week4.domain.toDoCard.model

import jakarta.persistence.*
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