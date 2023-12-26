package com.teamsparta.courseregistration.domain.couseapplication.dto

import com.teamsparta.courseregistration.domain.course.dto.CourseResponse
import com.teamsparta.courseregistration.domain.user.dto.UserResponse

data class CourseApplicationResponse(
    val id: Long,
    val course: CourseResponse, // JSON 안에 JSON으로 나갈 것
    val user: UserResponse,
    val status: String,
)
