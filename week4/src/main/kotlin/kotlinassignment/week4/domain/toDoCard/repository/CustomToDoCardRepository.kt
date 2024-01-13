package kotlinassignment.week4.domain.toDoCard.repository

import kotlinassignment.week4.domain.toDoCard.model.ToDoCard

interface CustomToDoCardRepository {

    fun findAllFilterByUserNameAndOrderBySortOrder(
        userName: String?,
        sortOrder: String,
    ): List<ToDoCard>
}
