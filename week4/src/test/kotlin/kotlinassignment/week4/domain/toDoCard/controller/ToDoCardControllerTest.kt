package kotlinassignment.week4.domain.toDoCard.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.DescribeSpec
import io.mockk.every
import io.mockk.spyk
import kotlinassignment.week4.domain.toDoCard.dto.ToDoCardIsCompletePatchRequest
import kotlinassignment.week4.domain.toDoCard.dto.ToDoCardResponse
import kotlinassignment.week4.domain.toDoCard.service.ToDoCardService
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.patch
import java.time.LocalDateTime

// 참고
// - https://www.baeldung.com/kotlin/spring-boot-testing
// - https://www.baeldung.com/kotlin/mockk
// - https://techblog.woowahan.com/5825
@WebMvcTest(controllers = [ToDoCardController::class])
// exception handler 동작 간단하게 보기 위해 간단하게 @WebMvcTest 사용
// @ExtendWith(MockKExtension::class) cf. @WebMvcTest 사용하지 않는 방법 - https://mangkyu.tistory.com/244(Java 코드)
internal class ToDoCardControllerTest(
    /*
    @MockkBean private val toDoCardService: ToDoCardService = spyk<ToDoCardService>(),
    private val mockMvc: MockMvc,
    ) : DescribeSpec({

    describe("할 일 카드 완료 처리를 요청했을 때, (path variable을 포함한 URI는 문제 없는 경우)") {
        every { toDoCardService.completeToDoCard(any(), any()) } returns ToDoCardResponse(
            1, "", "", "", LocalDateTime.now(), true
        ) // 로직 수행까지 보고 싶은 게 아니라서 임시로 넣어둠 - 가짜 객체 넣는 방법 찾아보기

        context("HTTP body가 Boolean 타입 isComplete를 정상적으로 포함하고 있는 JSON을 담고 있으면") {
            val normalRequestContent = ToDoCardIsCompletePatchRequest(true)
            it ("정상 상태코드 응답을 확인할 수 있다.") {
                // mockMvc.perform() - https://sjparkk-dev1og.tistory.com/140
                // 링크처럼 Kotlin에서도 사용 가능하지만 아래 방식이 Kotlin에서 새로 도입된 방식인 듯하다.
                mockMvc.patch("/todocards/1") {
                    accept = MediaType.ALL
                    contentType = MediaType.APPLICATION_JSON
                    content = jacksonObjectMapper().writeValueAsString(normalRequestContent)
                }.andExpect {
                    status { isOk() }
                }
            }
        }

        context("HTTP body가 JSON 형태를 갖추고 있지만, isComplete가 없거나, isComplete가 있지만 값이 없다면") {
            val emptyJsonFormat = "{}"
            it ("400 Bad Request 상태코드 응답과 함께 특정 에러 메시지를 준다.") {
                mockMvc.patch("/todocards/1") {
                    accept = MediaType.ALL
                    contentType = MediaType.APPLICATION_JSON
                    content = emptyJsonFormat
                }.andExpect {
                    status { isBadRequest() }
                    jsonPath("$.message") { value("완료 여부는 필수값입니다.") }
                }
            }
        }

        context("HTTP body가 JSON 형태를 갖추고 있지 않거나, isComplete에 Boolean 타입이 아닌 값을 담고 있다면") {
            val wrongJsonFormat = "{ \"isComplete\": "
            it ("404 NotFound 상태코드 응답과 함께 특정 에러 메시지를 준다.") {
                mockMvc.patch("/todocards/1") {
                    accept = MediaType.ALL
                    contentType = MediaType.APPLICATION_JSON
                    content = wrongJsonFormat
                }.andExpect {
                    status { isNotFound() }
                    jsonPath("$.message") { value("요청의 body 중 형식이 적절하지 않은 데이터가 입력되었습니다.") }
                }
            }
        }
    }
}*/)
