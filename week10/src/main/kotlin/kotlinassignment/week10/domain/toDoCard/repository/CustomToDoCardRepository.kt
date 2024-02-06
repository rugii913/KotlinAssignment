package kotlinassignment.week10.domain.toDoCard.repository

import kotlinassignment.week10.domain.toDoCard.model.ToDoCard
import org.springframework.data.domain.Pageable

interface CustomToDoCardRepository {

    fun findAllFilteringByTitleOrUserNameWithSortOrder(
        title: String?,
        memberNickname: String?,
        pageable: Pageable,
    ): List<ToDoCard>
}
