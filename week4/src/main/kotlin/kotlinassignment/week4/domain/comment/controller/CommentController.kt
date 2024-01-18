package kotlinassignment.week4.domain.comment.controller

import kotlinassignment.week4.domain.comment.dto.CommentCreateRequest
import kotlinassignment.week4.domain.comment.dto.CommentResponse
import kotlinassignment.week4.domain.comment.dto.CommentUpdateRequest
import kotlinassignment.week4.domain.comment.service.CommentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/todocards/{toDoCardId}/comments/")
@RestController
class CommentController(
    private val commentService: CommentService
) {

    @PostMapping
    fun createComment(
        @PathVariable toDoCardId: Long,
        @RequestBody request: CommentCreateRequest,
    ): ResponseEntity<CommentResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(commentService.createComment(toDoCardId, request))
    }

    @PutMapping("/{commentId}")
    fun updateComment(
        @PathVariable toDoCardId: Long,
        @PathVariable commentId: Long,
        @RequestBody request: CommentUpdateRequest,
    ): ResponseEntity<CommentResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.updateComment(toDoCardId, commentId, request))
    }

    @DeleteMapping("/{commentId}")
    fun deleteComment(
        @PathVariable toDoCardId: Long,
        @PathVariable commentId: Long,
    ): ResponseEntity<Unit> {
        commentService.deleteComment(toDoCardId, commentId)

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}
