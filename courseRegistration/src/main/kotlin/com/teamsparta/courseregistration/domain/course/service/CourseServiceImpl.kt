package com.teamsparta.courseregistration.domain.course.service

import com.teamsparta.courseregistration.domain.course.dto.CourseResponse
import com.teamsparta.courseregistration.domain.course.dto.CreateCourseRequest
import com.teamsparta.courseregistration.domain.course.dto.UpdateCourseRequest
import org.springframework.stereotype.Service

@Service
class CourseServiceImpl : CourseService {

    override fun getAllCourseList(): List<CourseResponse> {
        // TODO: DB에서 모든 Course 목록을 조회하여 CourseResponse 목록으로 변환 후 반환
        TODO("Not yet implemented")
    }

    override fun getCourseById(courseId: Long): CourseResponse {
        // TODO: DB에서 ID 기반으로 Course를 가져와서 CourseResponse로 변환 후 반한
        TODO("Not yet implemented")
    }

    override fun createCourse(request: CreateCourseRequest): CourseResponse {
        // TODO: request를 Course로 변환 후 DB에 저장
        TODO("Not yet implemented")
    }

    override fun updateCourse(courseId: Long, request: UpdateCourseRequest): CourseResponse {
        // TODO: DB에서 courseId에 해당하는 Course를 가져와서 request 기반으로 업데이트 후 DB에 저장, 저장한 결과를 CourseResponse로 변환 후 반환 
        TODO("Not yet implemented")
    }

    override fun deleteCourse(courseId: Long) {
        // TODO: DB에서 courseId에 해당하는 Course를 삭제, 정책 상 CourseApplication, Lecture는 연관된 Course의 life cycle을 함께하므로 모두 삭제
        TODO("Not yet implemented")
    }
}