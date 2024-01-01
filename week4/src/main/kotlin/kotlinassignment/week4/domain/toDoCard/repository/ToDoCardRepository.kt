package kotlinassignment.week4.domain.toDoCard.repository

import kotlinassignment.week4.domain.toDoCard.model.ToDoCard
import org.springframework.data.jpa.repository.JpaRepository

interface ToDoCardRepository : JpaRepository<ToDoCard, Long> {
    fun findAllByOrderByCreatedDateTimeDescIdDesc(): List<ToDoCard>
}
