package kotlinassignment.week4.domain.comment.service

import kotlinassignment.week4.domain.comment.dto.CommentCreateRequest
import kotlinassignment.week4.domain.comment.dto.CommentResponse
import kotlinassignment.week4.domain.comment.dto.CommentUpdateRequest
import kotlinassignment.week4.domain.comment.model.Comment
import kotlinassignment.week4.domain.comment.model.fromRequestToComment
import kotlinassignment.week4.domain.comment.model.toResponse
import kotlinassignment.week4.domain.comment.model.updateFrom
import kotlinassignment.week4.domain.comment.repository.CommentRepository
import kotlinassignment.week4.domain.exception.IncorrectRelatedEntityIdException
import kotlinassignment.week4.domain.exception.ModelNotFoundException
import kotlinassignment.week4.domain.exception.PasswordMismatchException
import kotlinassignment.week4.domain.toDoCard.model.addComment
import kotlinassignment.week4.domain.toDoCard.repository.ToDoCardRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentService(
    private val toDoCardIdRepository: ToDoCardRepository,
    private val commentRepository: CommentRepository,
) {

    @Transactional
    fun createComment(toDoCardId: Long, request: CommentCreateRequest): CommentResponse {
        val targetToDoCard =
            toDoCardIdRepository.findByIdOrNull(toDoCardId) ?: throw ModelNotFoundException("ToDoCard", toDoCardId)

        val comment = fromRequestToComment(request, targetToDoCard)
        targetToDoCard.addComment(comment)
        toDoCardIdRepository.save(targetToDoCard)

        return comment.toResponse()
    }

    @Transactional
    fun updateComment(toDoCardId: Long, commentId: Long, request: CommentUpdateRequest): CommentResponse {
        // 해당 commentId 존재 여부만 먼저 확인 후, 존재하지 않는 경우 예외 처리, 존재하는 경우 비밀번호 검사 후 entity 가져옴
        // - select를 두 번 이상 하게 되나, 각 경우에 따라 다른 방식으로 예외처리하기 위해 이렇게 구현하고자 했음
        if (!commentRepository.existsById(commentId)) throw ModelNotFoundException("Comment", commentId)
        val targetComment = getCommentIfUserNameAndPasswordMatches(commentId, TODO(), TODO())

        if (targetComment.toDoCard.id != toDoCardId) { // Comment의 toDoCard 참조 FetchType.Lazy 지정하지 않을 경우 불필요하게 ToDoCard select 쿼리가 한 번 더 나감
            throw IncorrectRelatedEntityIdException("Comment", commentId, "ToDoCard", toDoCardId)
        }

        targetComment.updateFrom(request)
        commentRepository.save(targetComment)

        return targetComment.toResponse()
    }

    @Transactional
    fun deleteComment(toDoCardId: Long, commentId: Long): Unit {
        // 해당 commentId 존재 여부만 먼저 확인 후, 존재하지 않는 경우 예외 처리, 존재하는 경우 비밀번호 검사 후 entity 가져옴
        // - select를 두 번 이상 하게 되나, 각 경우에 따라 다른 방식으로 예외처리하기 위해 이렇게 구현하고자 했음
        if (!commentRepository.existsById(commentId)) throw ModelNotFoundException("Comment", commentId)
        val targetComment = getCommentIfUserNameAndPasswordMatches(commentId, TODO(), TODO())

        if (targetComment.toDoCard.id != toDoCardId) { // Comment의 toDoCard 참조 FetchType.Lazy 지정하지 않을 경우 불필요하게 ToDoCard select 쿼리가 한 번 더 나감
            throw IncorrectRelatedEntityIdException("Comment", commentId, "ToDoCard", toDoCardId)
        }

        commentRepository.deleteById(commentId)
    }

    private fun getCommentIfUserNameAndPasswordMatches(commentId: Long, userNameRequested: String, passwordRequested: String): Comment {
        // ?? propagation = PROPAGATION.REQUIRED로 될까? 이렇게 따로 추출한 메서드는 transaction 처리 어떻게 되는지 알아보기
        // 암호화 될 password는 서버까지 끌고 오지 않고, DB 서버 내에서 비교 후 일치하면 처리되도록 할 것
        return commentRepository.findByIdAndUserNameAndPassword(commentId, userNameRequested, passwordRequested)
            ?: throw PasswordMismatchException("Comment", commentId)
    }
}
