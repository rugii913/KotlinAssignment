package kotlinassignment.week4.domain.toDoCard.dto

import jakarta.validation.constraints.NotBlank

data class ToDoCardUpdateRequest(
    @field: NotBlank(message = "제목은 필수값입니다.") val title: String?,
    val description: String?,
)
