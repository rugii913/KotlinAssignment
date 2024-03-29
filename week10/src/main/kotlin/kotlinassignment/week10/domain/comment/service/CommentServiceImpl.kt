package kotlinassignment.week10.domain.comment.service

import kotlinassignment.week10.domain.comment.dto.*
import kotlinassignment.week10.domain.comment.model.Comment
import kotlinassignment.week10.domain.comment.repository.CommentRepository
import kotlinassignment.week10.domain.exception.ModelNotFoundException
import kotlinassignment.week10.domain.exception.UnauthorizedAccessException
import kotlinassignment.week10.domain.member.repository.MemberRepository
import kotlinassignment.week10.domain.toDoCard.repository.ToDoCardRepository
import kotlinassignment.week10.infra.security.MemberPrincipal
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class CommentServiceImpl(
    private val toDoCardIdRepository: ToDoCardRepository,
    private val commentRepository: CommentRepository,
    private val memberRepository: MemberRepository,
) : CommentService {

    override fun getCommentList(toDoCardId: Long, pageable: Pageable): Page<CommentResponse> {
        return commentRepository.findByToDoCard_IdAndDeletedAtIsNullOrderByIdDesc(toDoCardId, pageable).map { it.toResponse() }
    }

    @Transactional
    override fun createComment(toDoCardId: Long, request: CommentCreateRequest, memberPrincipal: MemberPrincipal): CommentResponse {
        val targetToDoCard = toDoCardIdRepository.findByIdOrNull(toDoCardId) ?: throw ModelNotFoundException("ToDoCard", toDoCardId)
        val member = memberRepository.findByIdOrNull(memberPrincipal.id) ?:throw ModelNotFoundException("Member", memberPrincipal.id)

        return Comment(
            content = request.content,
            member = member,
            toDoCard = targetToDoCard,
        ).let { commentRepository.save(it).toResponse() }
    }

    @Transactional
    override fun updateComment(
        toDoCardId: Long,
        commentId: Long,
        request: CommentUpdateRequest,
        memberPrincipal: MemberPrincipal
    ): CommentResponse {
        val targetComment = commentRepository.findByIdAndToDoCard_IdAndDeletedAtIsNull(commentId, toDoCardId)
            ?: throw ModelNotFoundException("Comment", commentId)
        check(targetComment.member.id == memberPrincipal.id) { throw UnauthorizedAccessException() }

        return targetComment.updateFrom(request).toResponse()
    }

    @Transactional
    override fun deleteComment(toDoCardId: Long, commentId: Long, memberPrincipal: MemberPrincipal): Unit {
        val targetComment = commentRepository.findByIdAndToDoCard_IdAndDeletedAtIsNull(commentId, toDoCardId)
            ?: throw ModelNotFoundException("Comment", commentId)
        check(targetComment.member.id == memberPrincipal.id) { throw UnauthorizedAccessException() }

        targetComment.deletedAt = LocalDateTime.now()
    }
}
