package kotlinassignment.week4and8.domain.toDoCard.repository

import kotlinassignment.week4and8.domain.toDoCard.model.ToDoCard

interface CustomToDoCardRepository {

    fun findAllFilterByUserNameAndOrderBySortOrder(
        userName: String?,
        sortOrder: String,
    ): List<ToDoCard>
}
