package kotlinassignment.week4.domain.toDoCard.dto

data class ToDoCardUpdateRequest(
    val title: String,
    val description: String?,
)
