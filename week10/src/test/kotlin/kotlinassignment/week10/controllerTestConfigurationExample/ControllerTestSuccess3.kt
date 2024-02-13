package kotlinassignment.week10.controllerTestConfigurationExample

import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import jakarta.servlet.http.HttpServletResponse
import kotlinassignment.week10.domain.toDoCard.controller.ToDoCardController
import kotlinassignment.week10.domain.toDoCard.dto.ToDoCardResponse
import kotlinassignment.week10.domain.toDoCard.service.ToDoCardService
import kotlinassignment.week10.infra.jwt.JwtUtil
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestConstructor
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import java.time.LocalDateTime

@ActiveProfiles("test-jwt")
@ContextConfiguration(classes = [ControllerTest3TestConfig::class])
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@WebMvcTest(controllers = [ToDoCardController::class], )
class ControllerTest3(
    private val mockMvc: MockMvc,
    @MockkBean private val toDoCardService: ToDoCardService,
    jwtUtil: JwtUtil,
) : AnnotationSpec() {

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

        val result = mockMvc.get("/todocards") {
            header("Authorization", "Bearer $jwtToken")
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
        }.andReturn()

        result.response.status shouldBe HttpServletResponse.SC_OK
    }
}

@ComponentScan(basePackages = ["kotlinassignment.week10.infra.jwt", "kotlinassignment.week10.infra.security"])
@TestConfiguration
class ControllerTest3TestConfig
