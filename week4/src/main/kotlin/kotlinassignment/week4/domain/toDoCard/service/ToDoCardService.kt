package kotlinassignment.week4.domain.toDoCard.service

import kotlinassignment.week4.domain.exception.ModelNotFoundException
import kotlinassignment.week4.domain.toDoCard.dto.ToDoCardCreateRequest
import kotlinassignment.week4.domain.toDoCard.dto.ToDoCardResponse
import kotlinassignment.week4.domain.toDoCard.dto.ToDoCardResponseWithComments
import kotlinassignment.week4.domain.toDoCard.dto.ToDoCardUpdateRequest
import kotlinassignment.week4.domain.toDoCard.model.ToDoCard
import kotlinassignment.week4.domain.toDoCard.model.toResponse
import kotlinassignment.week4.domain.toDoCard.model.toResponseWithComments
import kotlinassignment.week4.domain.toDoCard.repository.ToDoCardRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class ToDoCardService(
    private val toDoCardRepository: ToDoCardRepository,
) {
    // TODO: request dto의 필드들의 타입을 null 가능 타입으로 바꿨는데, dto를 entity로 변환하는 과정에서 !!를 사용하게 되어 보기 좋지 않다.
    // TODO: path variable에 mapping 되는 파라미터의 타입도 마찬가지

    fun getAllToDoCards(): List<ToDoCardResponse> {
        return toDoCardRepository.findAll().map(ToDoCard::toResponse)
    }

    fun getToDoCardById(toDoCardId: Long?): ToDoCardResponseWithComments {
        val toDoCard = toDoCardRepository.findByIdOrNull(toDoCardId) ?: throw ModelNotFoundException("ToDoCard", toDoCardId!!)

        return toDoCard.toResponseWithComments()
    }

    @Transactional
    fun createToDoCard(request: ToDoCardCreateRequest): ToDoCardResponse {
        val toDoCard = ToDoCard(
            title = request.title!!,
            description = request.description,
            userName = request.userName!!,
            createdDateTime = request.createdDateTime!!,
        )

        return toDoCardRepository.save(toDoCard).toResponse()
    }

    @Transactional
    fun updateToDoCard(toDoCardId: Long?, request: ToDoCardUpdateRequest): ToDoCardResponse {
        val toDoCard = toDoCardRepository.findByIdOrNull(toDoCardId) ?: throw ModelNotFoundException("ToDoCard", toDoCardId!!)

        // (해결?) request에 속성 추가, 정책상 변경 가능한 속성 변경 등 발생했을 때, 여기에서도 알 수 있도록 컴파일 에러를 내는 식으로 작성하려면 어떻게 해야 하는지?
        // -> 이런 이유 때문에 굳이 구조 분해 선언 사용했던 듯하다.
        val (title: String?, description: String?) = request
        toDoCard.title = title!!
        toDoCard.description = description!!

        return toDoCardRepository.save(toDoCard).toResponse()
    }

    @Transactional
    fun deleteToDoCard(toDoCardId: Long?): Unit {
        // 이 부분이 있어도 select query는 한 번만 나간다. 없는 id로 delete 시도하는 경우 확실한 에러 메시지를 주기 위함
        val toDoCard = toDoCardRepository.findByIdOrNull(toDoCardId) ?: throw ModelNotFoundException("ToDoCard", toDoCardId!!)

        return toDoCardRepository.delete(toDoCard)
    }
}
