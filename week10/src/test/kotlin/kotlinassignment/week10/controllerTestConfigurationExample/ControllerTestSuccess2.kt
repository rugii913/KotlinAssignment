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
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.FilterType
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestConstructor
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import java.time.LocalDateTime

@ActiveProfiles("test-jwt")
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@WebMvcTest(
    controllers = [ToDoCardController::class],
    excludeFilters = [ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = [JwtAuthenticationFilter::class])]
)
class ControllerTestSuccess2(
    private val mockMvc: MockMvc,
    @MockkBean private val toDoCardService: ToDoCardService,
) : AnnotationSpec() {

    @Test
    // @WithMockUser // 이게 동작하지 않고 계속 401 오류가 발생하여 다른 인증 방식을 찾음
    // 참고 - https://stackoverflow.com/questions/40471424/withmockuser-without-annotation
    fun test1() {
        SecurityContextHolder.getContext().authentication =
            PreAuthenticatedAuthenticationToken(null, null, listOf(SimpleGrantedAuthority("ROLE_USER")))

        every { toDoCardService.getToDoCardList(any(), any(), any(), any(), any(), any(), any()) }
            .returns(
                PageImpl(
                    listOf(ToDoCardResponse(1, LocalDateTime.now(), "test", "test", "test", false)),
                    Pageable.ofSize(5),
                    1
                )
            )

        val result =
            mockMvc.get("/todocards") {
                with(csrf())
            }
            .andReturn()

        result.response.status shouldBe HttpServletResponse.SC_OK
    }
}
