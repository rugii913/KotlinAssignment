package kotlinassignment.week4.domain.comment.controller

import kotlinassignment.week4.domain.comment.dto.CommentCreateRequest
import kotlinassignment.week4.domain.comment.dto.CommentDeleteRequest
import kotlinassignment.week4.domain.comment.dto.CommentResponse
import kotlinassignment.week4.domain.comment.dto.CommentUpdateRequest
import kotlinassignment.week4.domain.comment.service.CommentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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
        @RequestBody request: CommentDeleteRequest,
    ): ResponseEntity<Unit> {
        commentService.deleteComment(toDoCardId, commentId, request)

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}
