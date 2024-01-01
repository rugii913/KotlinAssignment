package kotlinassignment.week4.domain.comment.repository;

import kotlinassignment.week4.domain.comment.model.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long> {

    fun findByIdAndPassword(commentId: Long, password: String): Comment?
}
