package kotlinassignment.week4

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Week4Application

fun main(args: Array<String>) {
    runApplication<Week4Application>(*args)
}
/**
 * TODO
 *  - ToDoCard.kt
 *    - 55줄 ToDoCard의 comments를 사용하지 않고, comment repository에서 페이징 처리한 comment를 가져오며 사용하지 않게 된 메서드
 *      ToDoCard가 참조하고 있는 comments를 사용하지 않고, comment repository 쪽에서 따로 Page 처리된 Comment들을 가져옴
 *      ToDoCard에서 직접 comments를 꺼내올 때도 paging 처리되게 하려면 어떻게 해야하는지 알아보기
 *  - ToDoCardService.kt
 *    - 31줄 ToDoCard가 참조하고 있는 comments를 사용하지 않고, comment repository 쪽에서 따로 Page 처리된 Comment들을 가져옴
 *          ToDoCard에서 직접 comments를 꺼내올 때도 paging 처리되게 하려면 어떻게 해야하는지 알아보기
 *    - 34줄 이 부분은 임시로 값을 넣어놨음, 추후 제대로 처리할 것
 *    - 43줄 길이 검증 - 더 나은 방법이 없는지 고민
 *  - ToDoCardIsCompletePatchRequest.kt
 *    - 20줄 작동 방식 알아보기 - 생각보다 작동 방식이 어려운 듯함
 *      private이 빠지면 Jackson 매핑 에러가 발생하고, val isComplete = _isComplete!!으로 받아오려고 하면 validation이 안 됨
 *  - CommentService.kt
 *    - 71줄 propagation = PROPAGATION.REQUIRED로 될까? 이렇게 따로 추출한 메서드는 transaction 처리 어떻게 되는지 알아보기
 */
