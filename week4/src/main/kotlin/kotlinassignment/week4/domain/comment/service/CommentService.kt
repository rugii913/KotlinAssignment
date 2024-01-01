package kotlinassignment.week4.domain.comment.service

import kotlinassignment.week4.domain.comment.dto.CommentCreateRequest
import kotlinassignment.week4.domain.comment.dto.CommentDeleteRequest
import kotlinassignment.week4.domain.comment.dto.CommentResponse
import kotlinassignment.week4.domain.comment.dto.CommentUpdateRequest
import kotlinassignment.week4.domain.comment.model.fromRequestToComment
import kotlinassignment.week4.domain.comment.model.toResponse
import kotlinassignment.week4.domain.comment.model.updateFrom
import kotlinassignment.week4.domain.comment.repository.CommentRepository
import kotlinassignment.week4.domain.exception.ModelNotFoundException
import kotlinassignment.week4.domain.toDoCard.model.addComment
import kotlinassignment.week4.domain.toDoCard.repository.ToDoCardRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
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
        // 방법 1 - select를 두 번 날리는 방법, commentRepository에는 접근하지 않고 ToDoCard를 다루면서 그 안의 Comment를 처리
        /*
        val targetToDoCard =
            toDoCardIdRepository.findByIdOrNull(toDoCardId) ?: throw ModelNotFoundException("ToDoCard", toDoCardId)

        val comment =
            targetToDoCard.comments.firstOrNull { it.id == commentId }
                ?: throw ModelNotFoundException("Comment", commentId)

        comment.updateFrom(request)
        toDoCardIdRepository.save(targetToDoCard)

        return comment.toResponse()
         */

        // 방법 2 - commentRepository에 접근, to_do_card 테이블과 comment 테이블 join
        val targetComment =
            commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException("Comment", commentId)
        if (targetComment.toDoCard.id != toDoCardId) {
            TODO("새로운 에러 클래스 만들어서 던지게 하기 - path variable로 받은 toDoCardId와 Comment가 참조하는 ToDoCard의 id가 일치하지 않는 경우")
        }

        targetComment.updateFrom(request)
        commentRepository.save(targetComment)

        return targetComment.toResponse()
    }

    @Transactional
    fun deleteComment(toDoCardId: Long, commentId: Long, request: CommentDeleteRequest): Unit {
        // 방법 1 - select를 두 번 날리는 방법, commentRepository에는 접근하지 않고 ToDoCard를 다루면서 그 안의 Comment를 처리
        /*
        val targetToDoCard =
            toDoCardIdRepository.findByIdOrNull(toDoCardId)
                ?: TODO("새로운 에러 클래스 만들어서 던지게 하기 - path variable로 받은 toDoCardId와 Comment가 참조하는 ToDoCard의 id가 일치하지 않는 경우")

        val comment =
            targetToDoCard.comments.firstOrNull { it.id == commentId } ?: throw ModelNotFoundException("Comment", commentId)

        targetToDoCard.comments.remove(comment)
        toDoCardIdRepository.save(targetToDoCard)
        */

        // 방법 2 - commentRepository에 접근, to_do_card 테이블과 comment 테이블 join
        val targetComment =
            commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException("Comment", commentId)
        if (targetComment.toDoCard.id != toDoCardId) {
            TODO("새로운 에러 클래스 만들어서 던지게 하기 - path variable로 받은 toDoCardId와 Comment가 참조하는 ToDoCard의 id가 일치하지 않는 경우")
        }

        commentRepository.deleteById(commentId)
    }
}
