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
 *    - 50줄 ToDoCard의 comments를 사용하지 않고, comment repository에서 페이징 처리한 comment를 가져오며 사용하지 않게 된 메서드
 *      ToDoCard가 참조하고 있는 comments를 사용하지 않고, comment repository 쪽에서 따로 Page 처리된 Comment들을 가져옴
 *      ToDoCard에서 직접 comments를 꺼내올 때도 paging 처리되게 하려면 어떻게 해야하는지 알아보기
 *  - ToDoCardService.kt
 *    - 21줄 request dto의 필드들의 타입을 null 가능 타입으로 바꿨는데, dto를 entity로 변환하는 과정에서 !!를 사용하게 되어 보기 좋지 않다.
 *    - 22줄 path variable에 mapping 되는 파라미터의 타입도 마찬가지
 *    - 32줄 ToDoCard가 참조하고 있는 comments를 사용하지 않고, comment repository 쪽에서 따로 Page 처리된 Comment들을 가져옴
 *          ToDoCard에서 직접 comments를 꺼내올 때도 paging 처리되게 하려면 어떻게 해야하는지 알아보기
 *    - 35줄 이 부분은 임시로 값을 넣어놨음, 추후 제대로 처리할 것
 */
