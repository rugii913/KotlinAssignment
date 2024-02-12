package kotlinassignment.week10.domain.toDoCard.controller

import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.BeforeContainer
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import jakarta.servlet.http.HttpServletResponse
import kotlinassignment.week10.domain.toDoCard.dto.ToDoCardResponse
import kotlinassignment.week10.domain.toDoCard.service.ToDoCardService
import kotlinassignment.week10.infra.jwt.JwtUtil
import kotlinassignment.week10.testConfig.ControllerTestSecurityConfig
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
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
@ContextConfiguration(classes = [ControllerTestSecurityConfig::class])
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@WebMvcTest(controllers = [ToDoCardController::class])
class ToDoCardControllerTest(
    private val mockMvc: MockMvc,
    @MockkBean private val toDoCardService: ToDoCardService,
    jwtUtil: JwtUtil,
) : DescribeSpec({

    val jwtToken = jwtUtil.generateAccessToken(
        subject = "1",
        email = "test@testtest.com"
    )

    val uri = "/todocards"
    var queryParameter: String?
    var pathVariable: String?

    beforeTest {
        queryParameter = null
        pathVariable = null
    }

    describe("GET /todocards") {
        context("쿼리 파라미터 중 page에") {
            it("숫자가 아닌 값이 들어오면 특정 에러를 보낸다.") {
            }
            it("0보다 작은 숫자가 들어오면 에러를 보낸다.") {
            }
            it("숫자 0이 들어오면 0 페이지에 있는 ToDoCard들을 응답한다.") {
            }
            // sort는 더 생각해볼 것
        }
    }

    describe("GET /todocards/{toDoCardId}") {
        context("path variable에") {
            it("숫자가 아닌 값이 들어오면 특정 에러를 보낸다.") {
            }
            it("숫자가 1이 들어오면 1번 ToDoCard를 응답한다.") {
            }
        }
    }

    describe("POST /todocards") {
        context("JSON 형식 body에") {
            it("title에 빈 String이 들어오면 특정 에러를 보낸다.") {
            }
            it("description에 빈 String이 들어오면 특정 에러를 보낸다.") {
            }
            it("title, description에 비어있지 않은 String이 들어오면, 이들을 title, description으로 갖고 있는 ToDoCard를 응답한다.") {
            }
        }
    }

    describe("PUT /todocards/{toDoCardId}") {
        context("JSON 형식 body에 ") {
            it("title에 빈 String이 들어오면 특정 에러를 보낸다.") {
            }
            it("description에 빈 String이 들어오면 특정 에러를 보낸다.") {
            }
        }
    }

    describe("PATCH /todocards/{toDoCardId}") {
        context("JSON 형식 body에") {
            it("isComplete에 빈 String이 들어오면 특정 에러를 보낸다.") {
            }
        }
    }

    describe("DELETE /todocards/{toDoCardId}") {
        context("정상적으로 호출되면") {
            it("No Content 응답을 보낸다.") {
            }
        }
    }
})
