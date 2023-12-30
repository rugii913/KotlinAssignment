package kotlinassignment.week4.domain.toDoCard.dto

import jakarta.validation.constraints.NotNull

data class ToDoCardIsCompletePatchRequest(
    @field: NotNull(message = "완료 여부는 필수값입니다.") val isComplete: Boolean?, // validation 하지는 않고, body가 제대로 안 들어오면 isComplete를 false로 바꾸게 함
)
