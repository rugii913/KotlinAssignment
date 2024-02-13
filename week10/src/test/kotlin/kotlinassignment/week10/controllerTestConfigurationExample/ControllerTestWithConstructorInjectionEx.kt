package kotlinassignment.week10.controllerTestConfigurationExample

import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.AnnotationSpec
import kotlinassignment.week10.domain.toDoCard.controller.ToDoCardController
import kotlinassignment.week10.domain.toDoCard.service.ToDoCardService
import kotlinassignment.week10.infra.jwt.JwtUtil
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.test.context.TestConstructor
import org.springframework.test.context.TestPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

@TestPropertySource(
    // locations = ["classpath:application-jwt.yml"] // locations에 yaml은 지원하지 않음 https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/test/context/TestPropertySource.html
    properties = [
        "authentication.member.jwt.issuer = temp",
        "authentication.member.jwt.secret = ABCDEabcde1234567890ABCDEabcde12",
        "authentication.member.jwt.accessTokenExpirationHour = 1",
    ]
)
@Import(JwtUtil::class) // @ContextConfiguration(classes = [JwtUtil::class]) // https://stackoverflow.com/questions/48341097/import-vs-contextconfiguration-in-spring
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL) // @Autowired constructor(~)를 사용할 수도 있을 것
@WebMvcTest(controllers = [ToDoCardController::class])
class ControllerTestWithConstructorInjectionEx(
    private val mockMvc: MockMvc,
    @MockkBean private val toDoCardService: ToDoCardService,
    jwtUtil: JwtUtil,
) : AnnotationSpec() {

    private val jwtToken = jwtUtil.generateAccessToken(
        subject = "1",
        email = "test@testtest.com"
    )

    @Test
    fun test() {
        val result = mockMvc.perform(
            get("/todocards")
                .header("Authorization", "Bearer $jwtToken")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
    }
}
