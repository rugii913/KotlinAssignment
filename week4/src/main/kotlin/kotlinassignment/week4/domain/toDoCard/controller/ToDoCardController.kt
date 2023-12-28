package kotlinassignment.week4.domain.toDoCard.controller

import kotlinassignment.week4.domain.exception.ModelNotFoundException
import kotlinassignment.week4.domain.toDoCard.dto.ToDoCardCreateRequest
import kotlinassignment.week4.domain.toDoCard.dto.ToDoCardResponse
import kotlinassignment.week4.domain.toDoCard.dto.ToDoCardUpdateRequest
import kotlinassignment.week4.domain.toDoCard.model.ToDoCard
import kotlinassignment.week4.domain.toDoCard.model.toResponse
import kotlinassignment.week4.domain.toDoCard.repository.ToDoCardRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController("/todocards")
class ToDoCardController(
     private val toDoCardRepository: ToDoCardRepository
) {

    @GetMapping
    fun getToDoCardList(): ResponseEntity<List<ToDoCardResponse>> {
        return ResponseEntity
            .status(200)
            .body(toDoCardRepository.findAll().map(ToDoCard::toResponse))
    }

    @GetMapping("/{toDoCardId}")
    fun getToDoCard(@PathVariable toDoCardId: Long): ResponseEntity<ToDoCardResponse> {
        val toDoCard = toDoCardRepository.findByIdOrNull(toDoCardId) ?: throw ModelNotFoundException("ToDoCard", toDoCardId)

        return ResponseEntity
            .status(200)
            .body(toDoCard.toResponse())
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
        val toDoCard = toDoCardRepository.findByIdOrNull(toDoCardId) ?: throw ModelNotFoundException("ToDoCard", toDoCardId)

        // TODO: request에 속성 추가, 정책상 변경 가능한 속성 변경 등 발생했을 때, 여기에서도 알 수 있도록 컴파일 에러를 내는 식으로 작성하려면 어떻게 해야 하는지 고민
        toDoCard.title = todoCardUpdateRequest.title
        toDoCard.description = todoCardUpdateRequest.description

        return ResponseEntity
            .status(200)
            .body(toDoCardRepository.save(toDoCard).toResponse())
    }

    @DeleteMapping("/{toDoCardId}")
    fun deleteToDoCard(@PathVariable toDoCardId: Long): ResponseEntity<Unit> {
        // 이 부분을 추가해도 select query는 한 번만 나간다.
        // deleteByIdOrNull() 기본 메서드는 없기에, 없는 id로 delete 시도하는 경우 확실한 에러 메시지를 주기 위함
        val toDoCard = toDoCardRepository.findByIdOrNull(toDoCardId) ?: throw ModelNotFoundException("ToDoCard", toDoCardId)

        return ResponseEntity
            .status(200)
            .body(toDoCardRepository.delete(toDoCard))
    }
}
