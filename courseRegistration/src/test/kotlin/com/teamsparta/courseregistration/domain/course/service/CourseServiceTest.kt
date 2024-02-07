package com.teamsparta.courseregistration.domain.course.service

import com.teamsparta.courseregistration.domain.course.repository.CourseRepository
import com.teamsparta.courseregistration.domain.couseapplication.repository.CourseApplicationRepository
import com.teamsparta.courseregistration.domain.exception.ModelNotFoundException
import com.teamsparta.courseregistration.domain.lecture.repository.LectureRepository
import com.teamsparta.courseregistration.domain.user.repository.UserRepository
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.extensions.spring.SpringExtension
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull

@SpringBootTest
@ExtendWith(MockKExtension::class)
class CourseServiceTest : BehaviorSpec({
    extension(SpringExtension)

    afterContainer {
        clearAllMocks()
    }

    val courseRepository = mockk<CourseRepository>()
    val lectureRepository = mockk<LectureRepository>()
    val courseApplicationRepository = mockk<CourseApplicationRepository>()
    val userRepository = mockk<UserRepository>()

    // 생성자 주입이므로 이런 방식이 가능, 빈으로 띄워진 것 가져오지 않고, 직접 생성한 courseService 사용
    val courseService = CourseServiceImpl(courseRepository, lectureRepository, courseApplicationRepository, userRepository)

    Given("Course 목록이 존재하지 않을 때") {

        When("특정 Course를 요청하면") {

            Then("ModelNotFoundException이 발생해야 한다.") {

                // given
                val courseId = 1L
                every { courseRepository.findByIdOrNull(any()) } returns null

                shouldThrow<ModelNotFoundException> {
                    courseService.getCourseById(courseId)
                }
            }
        }
    }
})
