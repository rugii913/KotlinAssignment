package kotlinassignment.week4.domain.comment.service

import kotlinassignment.week4.domain.comment.dto.CommentCreateRequest
import kotlinassignment.week4.domain.comment.dto.CommentDeleteRequest
import kotlinassignment.week4.domain.comment.dto.CommentResponse
import kotlinassignment.week4.domain.comment.dto.CommentUpdateRequest
import kotlinassignment.week4.domain.comment.repository.CommentRepository
import org.springframework.stereotype.Service

@Service
class CommentService(
    private val commentRepository: CommentRepository
) {
    fun createComment(toDoCardId: Long, request: CommentCreateRequest): CommentResponse? {
        TODO("Not yet implemented")
    }

    fun updateComment(toDoCardId: Long, commentId: Long, request: CommentUpdateRequest): CommentResponse? {
        TODO("Not yet implemented")
    }

    fun deleteComment(toDoCardId: Long, commentId: Long, request: CommentDeleteRequest) {
        TODO("Not yet implemented")
    }
}
