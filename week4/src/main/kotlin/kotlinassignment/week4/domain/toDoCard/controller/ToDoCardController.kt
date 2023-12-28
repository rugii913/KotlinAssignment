package kotlinassignment.week4.domain.toDoCard.controller

import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import kotlinassignment.week4.domain.exception.ModelNotFoundException
import kotlinassignment.week4.domain.toDoCard.dto.ToDoCardCreateRequest
import kotlinassignment.week4.domain.toDoCard.dto.ToDoCardResponse
import kotlinassignment.week4.domain.toDoCard.dto.ToDoCardUpdateRequest
import kotlinassignment.week4.domain.toDoCard.model.ToDoCard
import kotlinassignment.week4.domain.toDoCard.model.toResponse
import kotlinassignment.week4.domain.toDoCard.repository.ToDoCardRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*


@RequestMapping("/todocards")
@RestController
@Validated
class ToDoCardController(
     private val toDoCardRepository: ToDoCardRepository
) {
    // TODO: request dto의 필드들의 타입을 null 가능 타입으로 바꿨는데, dto를 entity로 변환하는 과정에서 !!를 사용하게 되어 보기 좋지 않다.
    // TODO: path variable에 mapping 되는 파라미터의 타입도 마찬가지
    // cf. url "/todocards/에 대한 GET: getToDoCard(), PUT: updateToDoCard(), DELETE: deleteToDoCard()는
    // path variable 없는 /todocards/{toDoCardId} 요청을 일괄적으로 handling 하기 위해 추가되었다.
    // required = false 방법은 작동하지 않았음

    @GetMapping
    fun getToDoCardList(): ResponseEntity<List<ToDoCardResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(toDoCardRepository.findAll().map(ToDoCard::toResponse))
    }

    @GetMapping("/{toDoCardId}", "/")
    fun getToDoCard(@PathVariable @NotNull(message = "id는 필수값입니다.") toDoCardId: Long?): ResponseEntity<ToDoCardResponse> {
        val toDoCard = toDoCardRepository.findByIdOrNull(toDoCardId) ?: throw ModelNotFoundException("ToDoCard", toDoCardId!!)

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(toDoCard.toResponse())
    }

    @PostMapping
    fun createToDoCard(@Valid @RequestBody request: ToDoCardCreateRequest): ResponseEntity<ToDoCardResponse> {

        val toDoCard = ToDoCard(
            title = request.title!!,
            description = request.description,
            userName = request.userName!!,
            createdDateTime = request.createdDateTime!!,
        )

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(toDoCardRepository.save(toDoCard).toResponse())
    }

    @PutMapping("/{toDoCardId}", "/")
    fun updateToDoCard(
        @PathVariable @NotNull(message = "id는 필수값입니다.") toDoCardId: Long?,
        @Valid @RequestBody request: ToDoCardUpdateRequest
    ): ResponseEntity<ToDoCardResponse> {
        val toDoCard = toDoCardRepository.findByIdOrNull(toDoCardId) ?: throw ModelNotFoundException("ToDoCard", toDoCardId!!)

        // TODO: request에 속성 추가, 정책상 변경 가능한 속성 변경 등 발생했을 때, 여기에서도 알 수 있도록 컴파일 에러를 내는 식으로 작성하려면 어떻게 해야 하는지 고민
        toDoCard.title = request.title!!
        toDoCard.description = request.description

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(toDoCardRepository.save(toDoCard).toResponse())
    }

    @DeleteMapping("/{toDoCardId}", "/")
    fun deleteToDoCard(@PathVariable @NotNull(message = "id는 필수값입니다.") toDoCardId: Long?): ResponseEntity<Unit> {
        // 이 부분을 추가해도 select query는 한 번만 나간다.
        // deleteByIdOrNull() 기본 메서드는 없기에, 없는 id로 delete 시도하는 경우 확실한 에러 메시지를 주기 위함
        val toDoCard = toDoCardRepository.findByIdOrNull(toDoCardId) ?: throw ModelNotFoundException("ToDoCard", toDoCardId!!)

        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .body(toDoCardRepository.delete(toDoCard))
    }
}
