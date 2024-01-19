package kotlinassignment.week4and8.domain.toDoCard.repository

import kotlinassignment.week4and8.domain.toDoCard.model.ToDoCard
import org.springframework.data.jpa.repository.JpaRepository

interface ToDoCardRepository : JpaRepository<ToDoCard, Long>, CustomToDoCardRepository {

    fun findAllByOrderByCreatedDateTimeDescIdDesc(): List<ToDoCard>
}
