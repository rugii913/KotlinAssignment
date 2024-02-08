package kotlinassignment.week10.controllerTestConfigurationExample

import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import jakarta.servlet.http.HttpServletResponse
import kotlinassignment.week10.domain.toDoCard.controller.ToDoCardController
import kotlinassignment.week10.domain.toDoCard.dto.ToDoCardResponse
import kotlinassignment.week10.domain.toDoCard.service.ToDoCardService
import kotlinassignment.week10.infra.security.JwtAuthenticationFilter
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.FilterType
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestConstructor
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import java.time.LocalDateTime

@ActiveProfiles("test-jwt")
// @Import(JwtUtil::class)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@AutoConfigureMockMvc(addFilters = false) // *****여기서 addFilters = false로 둬서 MockMVC 요청이 필터를 안 거치게 한다. default가 true*****
// @ExtendWith(MockKExtension::class) // 없어도 동작
@WebMvcTest(
    controllers = [ToDoCardController::class],
    excludeFilters = [ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = [JwtAuthenticationFilter::class])]
    // JWT 검증용 커스텀 필터 제외시킴
)
class ControllerTestSuccess1(
    private val mockMvc: MockMvc,
    @MockkBean private val toDoCardService: ToDoCardService,
    // jwtUtil: JwtUtil, // 의미 없음
) : AnnotationSpec() {

    // 의미 없음
//    private val jwtToken = jwtUtil.generateAccessToken(
//        subject = "1",
//        email = "test@testtest.com"
//    )

    @Test
    fun test1() {
        every { toDoCardService.getToDoCardList(any(), any(), any(), any(), any(), any(), any()) }
            .returns(
                PageImpl(
                    listOf(ToDoCardResponse(1, LocalDateTime.now(), "test", "test", "test", false)),
                    Pageable.ofSize(5),
                    1
                )
            )
                /*PageImpl(listOf(ToDoCardResponse(1, LocalDateTime.now(), "test", "test", "test", false)))*/

//        val result = mockMvc.perform(
//            get("/todocards")
//                .header("Authorization", "Bearer $jwtToken")
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//        ).andReturn()

//        val result = mockMvc.get("/todocards") {
//            header("Authorization", "Bearer $jwtToken")
//            contentType = MediaType.APPLICATION_JSON
//            accept = MediaType.APPLICATION_JSON
//        }.andReturn()
        // @WebMvcTest는 Spring Security를 auto-configure하기 때문에 의미가 없다. - @SpringBootTest와는 다름

        val result = mockMvc.get("/todocards").andReturn()

        result.response.status shouldBe HttpServletResponse.SC_OK
    }
}
