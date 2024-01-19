package kotlinassignment.week4and8.domain.comment.repository;

import kotlinassignment.week4and8.domain.comment.model.Comment
import kotlinassignment.week4and8.domain.toDoCard.model.ToDoCard
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long> {

    fun findByToDoCardOrderByCreatedDateTimeDesc(toDoCard: ToDoCard, pageable: Pageable): Page<Comment>
}
