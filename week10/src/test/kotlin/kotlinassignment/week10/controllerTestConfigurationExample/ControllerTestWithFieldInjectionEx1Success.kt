package kotlinassignment.week10.controllerTestConfigurationExample

import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.extensions.Extension
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.extensions.spring.SpringExtension
import kotlinassignment.week10.domain.toDoCard.controller.ToDoCardController
import kotlinassignment.week10.domain.toDoCard.service.ToDoCardService
import kotlinassignment.week10.infra.jwt.JwtUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@TestPropertySource(
    // locations = ["classpath:application-jwt.yml"] // locations에 yaml은 지원하지 않음 https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/test/context/TestPropertySource.html
    properties = [
        "authentication.member.jwt.issuer = test",
        "authentication.member.jwt.secret = ABCDEabcde1234567890ABCDEabcde12",
        "authentication.member.jwt.accessTokenExpirationHour = 1",
    ]
)
@ContextConfiguration(classes = [JwtUtil::class]) // @Import(JwtUtil::class) // https://stackoverflow.com/questions/48341097/import-vs-contextconfiguration-in-spring
@WebMvcTest(controllers = [ToDoCardController::class])
class ControllerTestWithFieldInjectionEx1Success : AnnotationSpec() {

    override fun extensions(): List<Extension> = listOf(SpringExtension)
    /*
       위 extensions가 없어서 필드 @Autowired가 동작하지 않았었음
       - https://jaehhh.tistory.com/147에 kotest 관련 트러블 슈팅이 자세히 나와 있음
       - 어차피 @WebMvcTest에 @ExtendWith({SpringExtension.class})가 포함되어 있는데 굳이 extensions()를 또 왜 명시해? 라고 생각했는데
         - kotest를 위해 다시 필요한 부분인 듯하다.
       - 구글링 키워드 "kotest lateinit var autowired problem"
    */
    @Autowired lateinit var mockMvc: MockMvc
    @MockkBean lateinit var toDoCardService: ToDoCardService
    @Autowired lateinit var jwtUtil: JwtUtil

    private val jwtToken by lazy { // by lazy로 넣지 않으면 에러 - lateinit property jwtUtil has not been initialized
        jwtUtil.generateAccessToken(
            subject = "1",
            email = "test@testtest.com"
        )
    }
//    private var jwtToken = jwtUtil.generateAccessToken(subject = "1", email = "test@testtest.com")

    @BeforeClass
    fun setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(ToDoCardController::class).build() // https://docs.spring.io/spring-framework/reference/testing/spring-mvc-test-framework/server-setup-options.html
    }

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
