package kotlinassignment.week4and8.domain.toDoCard.controller

import jakarta.validation.Valid
import kotlinassignment.week4and8.domain.toDoCard.dto.*
import kotlinassignment.week4and8.domain.toDoCard.service.ToDoCardService
import kotlinassignment.week4and8.infra.security.MemberPrincipal
import kotlinassignment.week4and8.infra.util.SortOrder
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
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
    fun getToDoCardList(
        @RequestParam(required = false) userName: String?,
        @RequestParam(required = false, name = "sort", defaultValue = "DESC") sortOrder: SortOrder,
    ): ResponseEntity<List<ToDoCardResponse>> {
        val toDoCardResponsesList = toDoCardService.getAllToDoCards(userName, sortOrder.name)

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(toDoCardResponsesList)
    }

    @GetMapping("/{toDoCardId}")
    fun getToDoCard(@PathVariable toDoCardId: Long): ResponseEntity<ToDoCardResponseWithComments> {
        val toDoCardResponseWithComments = toDoCardService.getToDoCardById(toDoCardId)

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(toDoCardResponseWithComments)
    }

    @PostMapping
    fun createToDoCard(
        @Valid @RequestBody request: ToDoCardCreateRequest,
        @AuthenticationPrincipal memberPrincipal: MemberPrincipal,
    ): ResponseEntity<ToDoCardResponse> {
        val toDoCardResponse = toDoCardService.createToDoCard(request, memberPrincipal)

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(toDoCardResponse)
    }

    @PutMapping("/{toDoCardId}")
    fun updateToDoCard(
        @PathVariable toDoCardId: Long,
        @Valid @RequestBody request: ToDoCardUpdateRequest,
        @AuthenticationPrincipal memberPrincipal: MemberPrincipal,
    ): ResponseEntity<ToDoCardResponse> {
        val toDoCardResponse = toDoCardService.updateToDoCard(toDoCardId, request, memberPrincipal)

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(toDoCardResponse)
    }

    @DeleteMapping("/{toDoCardId}")
    fun deleteToDoCard(
        @PathVariable toDoCardId: Long,
        @AuthenticationPrincipal memberPrincipal: MemberPrincipal,
    ): ResponseEntity<Unit> {
        toDoCardService.deleteToDoCard(toDoCardId, memberPrincipal)

        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }

    @PatchMapping("/{toDoCardId}")
    fun completeToDoCard(
        @PathVariable toDoCardId: Long,
        @Valid @RequestBody request: ToDoCardIsCompletePatchRequest,
        @AuthenticationPrincipal memberPrincipal: MemberPrincipal,
    ): ResponseEntity<ToDoCardResponse> {
        val toDoCardResponse = toDoCardService.completeToDoCard(toDoCardId, request, memberPrincipal)

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(toDoCardResponse)
    }
}
