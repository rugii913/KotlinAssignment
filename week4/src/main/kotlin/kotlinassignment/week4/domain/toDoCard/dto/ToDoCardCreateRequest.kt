package kotlinassignment.week4.domain.toDoCard.dto

import java.time.LocalDateTime

data class ToDoCardCreateRequest(
    val title: String,
    val description: String?,
    val userName: String,
    val createdDateTime: LocalDateTime,
)
