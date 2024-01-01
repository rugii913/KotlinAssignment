package kotlinassignment.week4.domain.comment.repository;

import kotlinassignment.week4.domain.comment.model.Comment
import kotlinassignment.week4.domain.toDoCard.model.ToDoCard
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long> {

    fun findByIdAndUserNameAndPassword(id: Long, userName: String, password: String): Comment?

    fun findByToDoCardOrderByCreatedDateTimeDesc(toDoCard: ToDoCard, pageable: Pageable): Page<Comment>
}
