package com.teamsparta.courseregistration.domain.course.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.teamsparta.courseregistration.domain.course.dto.CourseResponse
import com.teamsparta.courseregistration.domain.course.model.Course
import com.teamsparta.courseregistration.domain.course.model.CourseStatus
import com.teamsparta.courseregistration.domain.course.repository.CourseRepository
import com.teamsparta.courseregistration.domain.course.service.CourseService
import com.teamsparta.courseregistration.infra.security.jwt.JwtPlugin
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

@SpringBootTest
@AutoConfigureMockMvc // mockMvc 주입 용도
@ExtendWith(MockKExtension::class) // mockk 사용할 때 명시 필요
@ActiveProfiles("test")
class CourseControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
    private val jwtPlugin: JwtPlugin,
    private val courseRepository: CourseRepository,
) : DescribeSpec({
    extension(SpringExtension)

    afterContainer {
        clearAllMocks() // 컨테이너 기반으로 세팅을 했다면, 다른 컨테이너 실행될 때는 세팅된 mock을 지워준다.
    }

    val courseService = mockk<CourseService>()

    describe("GET /courses/{id}") {
        context("존재하는 ID를 요청할 때") {
            it("200 status code를 응답한다.") {
                val courseId = 1L

                every { courseService.getCourseById(any()) } returns CourseResponse(
                    id = courseId,
                    title = "test_title",
                    description = "abc",
                    status = "OPEN",
                    maxApplicants = 30,
                    numApplicants = 10,
                    lectures = mutableListOf(),
                ) // mocking - courseService.getCourseById(..) 호출되면 위 CourseResponse 반환, response 내용은 임의로 넣어둠

                val jwtToken = jwtPlugin.generateAccessToken(
                    subject = "1",
                    email = "test@test.com",
                    role = "STUDENT",
                )

                val result = mockMvc.perform(
                    get("/courses/$courseId") // MockMvcRequestBuilders의 get()임
                        .header("Authorization", "Bearer $jwtToken")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andReturn()
                    // .andExpect(status().isOk) - Kotest스럽게 MockMvcResultMatchers.andExpect() 사용하지 않고 result 받은 후 shouldBe로 확인

                result.response.status shouldBe 200

                // JSON 문자열에서 Kotlin 객체로 변환할 수 있는 방법들
                // 1. GSON - 라이브러리 종속성 추가 필요 2. JacksonObjectMapper - Java 기반이라 찜찜할 수 있음 3. kotlinx의 serialization - 플러그인 추가 필요
                // 여기서는 2. JacksonObjectMapper 사용 - 단점이 많지만, 간편하게 쓸 수 있다.
                val responseDto = jacksonObjectMapper().readValue(
                    result.response.contentAsString, // JSON String
                    CourseResponse::class.java // Java 기반이라 java 클래스로 받아야 함
                )

                responseDto.id shouldBe courseId
            }
        }
    }
})
