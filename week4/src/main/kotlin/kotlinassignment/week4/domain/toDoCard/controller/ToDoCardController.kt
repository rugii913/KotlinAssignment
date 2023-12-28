package kotlinassignment.week4.domain.toDoCard.controller

import kotlinassignment.week4.domain.toDoCard.dto.ToDoCardCreateRequest
import kotlinassignment.week4.domain.toDoCard.dto.ToDoCardResponse
import kotlinassignment.week4.domain.toDoCard.dto.ToDoCardUpdateRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController("/todocards")
class ToDoCardController {

    @GetMapping
    fun getToDoCardList(): ResponseEntity<List<ToDoCardResponse>> {
        return ResponseEntity.status(200).build()
    }

    @GetMapping("/{toDoCardId}")
    fun getToDoCard(@PathVariable toDoCardId: Long): ResponseEntity<ToDoCardResponse> {
        return ResponseEntity.status(200).build()
    }

    @PostMapping
    fun createToDoCard(@RequestBody todoCardCreateRequest: ToDoCardCreateRequest): ResponseEntity<ToDoCardResponse> {
        return ResponseEntity.status(200).build()
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
