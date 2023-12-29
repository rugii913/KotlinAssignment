package kotlinassignment.week4.domain.toDoCard.controller

import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import kotlinassignment.week4.domain.toDoCard.dto.ToDoCardCreateRequest
import kotlinassignment.week4.domain.toDoCard.dto.ToDoCardResponse
import kotlinassignment.week4.domain.toDoCard.dto.ToDoCardUpdateRequest
import kotlinassignment.week4.domain.toDoCard.service.ToDoCardService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*


@RequestMapping("/todocards")
@RestController
@Validated
class ToDoCardController(
    private val toDoCardService: ToDoCardService,
) {
    // cf. url "/todocards/에 대한 GET: getToDoCard(), PUT: updateToDoCard(), DELETE: deleteToDoCard()는
    // path variable 없는 /todocards/{toDoCardId} 요청을 일괄적으로 handling 하기 위해 추가되었다.
    // required = false 방법은 작동하지 않았음

    @GetMapping
    fun getToDoCardList(): ResponseEntity<List<ToDoCardResponse>> {
        val toDoCardResponsesList = toDoCardService.getAllToDoCards()

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(toDoCardResponsesList)
    }

    @GetMapping("/{toDoCardId}", "/")
    fun getToDoCard(@PathVariable @NotNull(message = "id는 필수값입니다.") toDoCardId: Long?): ResponseEntity<ToDoCardResponse> {
        val toDoCardResponse = toDoCardService.getToDoCardById(toDoCardId)

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(toDoCardResponse)
    }

    @PostMapping
    fun createToDoCard(@Valid @RequestBody request: ToDoCardCreateRequest): ResponseEntity<ToDoCardResponse> {
        val toDoCardResponse = toDoCardService.createToDoCard(request)

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(toDoCardResponse)
    }

    @PutMapping("/{toDoCardId}", "/")
    fun updateToDoCard(
        @PathVariable @NotNull(message = "id는 필수값입니다.") toDoCardId: Long?,
        @Valid @RequestBody request: ToDoCardUpdateRequest
    ): ResponseEntity<ToDoCardResponse> {
        val toDoCardResponse = toDoCardService.updateToDoCard(toDoCardId, request)

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(toDoCardResponse)
    }

    @DeleteMapping("/{toDoCardId}", "/")
    fun deleteToDoCard(@PathVariable @NotNull(message = "id는 필수값입니다.") toDoCardId: Long?): ResponseEntity<Unit> {
        toDoCardService.deleteToDoCard(toDoCardId)

        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }
}
