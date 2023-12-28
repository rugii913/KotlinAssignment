package kotlinassignment.week4.domain.toDoCard.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime

data class ToDoCardCreateRequest(
    @field: NotBlank(message = "제목은 필수값입니다.") val title: String?,
    val description: String?,
    @field: NotBlank(message = "작성자 이름은 필수값입니다.") val userName: String?,
    @field: NotNull(message = "작성일은 필수값입니다.") val createdDateTime: LocalDateTime?,
)
