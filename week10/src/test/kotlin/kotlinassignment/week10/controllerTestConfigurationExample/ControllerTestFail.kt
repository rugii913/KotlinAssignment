package kotlinassignment.week10.controllerTestConfigurationExample

import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.extensions.Extension
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import io.mockk.every
import jakarta.servlet.http.HttpServletResponse
import kotlinassignment.week10.domain.toDoCard.controller.ToDoCardController
import kotlinassignment.week10.domain.toDoCard.dto.ToDoCardResponse
import kotlinassignment.week10.domain.toDoCard.service.ToDoCardService
import kotlinassignment.week10.infra.jwt.JwtUtil
import kotlinassignment.week10.infra.security.JwtAuthenticationFilter
import kotlinassignment.week10.infra.security.SecurityConfig
import kotlinassignment.week10.infra.security.exception.CustomAccessDeniedHandler
import kotlinassignment.week10.infra.security.exception.CustomAuthenticationEntryPoint
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestConstructor
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import java.time.LocalDateTime

@ActiveProfiles("test-jwt")
@ContextConfiguration(classes = [
    JwtUtil::class,
    JwtAuthenticationFilter::class,
    CustomAccessDeniedHandler::class,
    CustomAuthenticationEntryPoint::class,
    SecurityConfig::class
]) // 알 수 없는 404 응답이 돌아옴 - no static resource ...
@WebAppConfiguration
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@WebMvcTest(controllers = [ToDoCardController::class], )
class ControllerTestFail(
    private val mockMvc: MockMvc,
    @MockkBean private val toDoCardService: ToDoCardService,
    jwtUtil: JwtUtil,
) : AnnotationSpec() {

    override fun extensions(): List<Extension> = listOf(SpringExtension)

    private val jwtToken = jwtUtil.generateAccessToken(
        subject = "1",
        email = "test@testtest.com"
    )

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

//        val result = mockMvc.get("/todocards") {
//            header("Authorization", "Bearer $jwtToken")
//            contentType = MediaType.APPLICATION_JSON
//            accept = MediaType.APPLICATION_JSON
//        }.andReturn()

        val result = mockMvc.perform(
            get("/todocards")
                .header("Authorization", "Bearer $jwtToken")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andReturn()

        result.response.status shouldBe HttpServletResponse.SC_OK
    }
}
