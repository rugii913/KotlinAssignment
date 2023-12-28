package kotlinassignment.week4.domain.toDoCard.controller

import kotlinassignment.week4.domain.toDoCard.dto.ToDoCardCreateRequest
import kotlinassignment.week4.domain.toDoCard.dto.ToDoCardResponse
import kotlinassignment.week4.domain.toDoCard.dto.ToDoCardUpdateRequest
import kotlinassignment.week4.domain.toDoCard.model.ToDoCard
import kotlinassignment.week4.domain.toDoCard.model.toResponse
import kotlinassignment.week4.domain.toDoCard.repository.ToDoCardRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController("/todocards")
class ToDoCardController(
     private val toDoCardRepository: ToDoCardRepository
) {

    @GetMapping
    fun getToDoCardList(): ResponseEntity<List<ToDoCardResponse>> {
        return ResponseEntity.status(200).build()
    }

    @GetMapping("/{toDoCardId}")
    fun getToDoCard(@PathVariable toDoCardId: Long): ResponseEntity<ToDoCardResponse> {
        return ResponseEntity.status(200).build()
    }

    @PostMapping
    fun createToDoCard(@RequestBody toDoCardCreateRequest: ToDoCardCreateRequest): ResponseEntity<ToDoCardResponse> {

        val toDoCard = ToDoCard(
            title = toDoCardCreateRequest.title,
            description = toDoCardCreateRequest.description,
            userName = toDoCardCreateRequest.userName,
            createdDateTime = toDoCardCreateRequest.createdDateTime,
        )

        return ResponseEntity
            .status(200)
            .body(toDoCardRepository.save(toDoCard).toResponse())
    }

    @PutMapping("/{toDoCardId}")
    fun updateToDoCard(
        @PathVariable toDoCardId: Long,
        @RequestBody todoCardUpdateRequest: ToDoCardUpdateRequest
    ): ResponseEntity<ToDoCardResponse> {
        return ResponseEntity.status(200).build()
    }

    @DeleteMapping("/{toDoCardId}")
    fun deleteToDoCard(@PathVariable toDoCardId: Long): ResponseEntity<Unit> {
        return ResponseEntity.status(200).build()
    }
}
