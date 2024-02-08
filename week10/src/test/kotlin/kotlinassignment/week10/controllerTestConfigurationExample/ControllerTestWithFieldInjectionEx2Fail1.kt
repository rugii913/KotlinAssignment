package kotlinassignment.week10.controllerTestConfigurationExample

import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.AnnotationSpec
import kotlinassignment.week10.domain.toDoCard.controller.ToDoCardController
import kotlinassignment.week10.domain.toDoCard.service.ToDoCardService
import kotlinassignment.week10.infra.jwt.JwtUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.Import
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@Import(WebTestConfigurationFail1and2::class)
@WebMvcTest(controllers = [ToDoCardController::class])
class ControllerTestWithFieldInjectionEx2Fail1 : AnnotationSpec() {

    @Autowired lateinit var mockMvc: MockMvc
    @MockkBean lateinit var toDoCardService: ToDoCardService
    @Autowired lateinit var jwtUtil: JwtUtil

    private val jwtToken by lazy { // by lazy로 넣지 않으면 에러 - lateinit property jwtUtil has not been initialized
        jwtUtil.generateAccessToken(
            subject = "1",
            email = "test@testtest.com"
        )
    }

    @BeforeClass
    fun setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(ToDoCardController::class).build() // https://docs.spring.io/spring-framework/reference/testing/spring-mvc-test-framework/server-setup-options.html
    }

    // 빌드 실패 방지용 주석 처리
//    @Test
//    fun test() {
//        val result = mockMvc.perform(
//            get("/todocards")
//                .header("Authorization", "Bearer $jwtToken")
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//        )
//    }
}
