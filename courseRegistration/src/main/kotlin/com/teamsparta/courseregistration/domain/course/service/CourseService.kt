package com.teamsparta.courseregistration.domain.course.service

import com.teamsparta.courseregistration.domain.course.dto.CourseResponse
import com.teamsparta.courseregistration.domain.course.dto.CreateCourseRequest
import com.teamsparta.courseregistration.domain.course.dto.UpdateCourseRequest
import com.teamsparta.courseregistration.domain.couseapplication.dto.ApplyCourseRequest
import com.teamsparta.courseregistration.domain.couseapplication.dto.CourseApplicationResponse
import com.teamsparta.courseregistration.domain.couseapplication.dto.UpdateApplicationStatusRequest
import com.teamsparta.courseregistration.domain.lecture.dto.AddLectureRequest
import com.teamsparta.courseregistration.domain.lecture.dto.LectureResponse
import com.teamsparta.courseregistration.domain.lecture.dto.UpdateLectureRequest
import com.teamsparta.courseregistration.infra.aop.StopWatch

interface CourseService {

    // @StopWatch // (cf.) 여기에 달아놓으면 작동하지 않음
    fun getAllCourseList(): List<CourseResponse>

    fun getCourseById(courseId: Long): CourseResponse

    fun createCourse(request: CreateCourseRequest): CourseResponse

    fun updateCourse(courseId: Long, request: UpdateCourseRequest): CourseResponse

    fun deleteCourse(courseId: Long)

    fun getLectureList(courseId: Long): List<LectureResponse>

    fun getLecture(courseId: Long, lectureId: Long): LectureResponse

    fun addLecture(courseId: Long, request: AddLectureRequest): LectureResponse

    fun updateLecture(
        courseId: Long,
        lectureId: Long,
        request: UpdateLectureRequest
    ): LectureResponse

    fun removeLecture(courseId: Long, lectureId: Long)

    fun applyCourse(courseId: Long, request: ApplyCourseRequest): CourseApplicationResponse

    fun getCourseApplication(
        courseId: Long,
        applicationId: Long
    ): CourseApplicationResponse

    fun getCourseApplicationList(courseId: Long): List<CourseApplicationResponse>

    fun updateCourseApplicationStatus(
        courseId: Long,
        applicationId: Long,
        request: UpdateApplicationStatusRequest
    ): CourseApplicationResponse
}