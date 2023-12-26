package com.teamsparta.courseregistration.domain.couseapplication.repository

import com.teamsparta.courseregistration.domain.couseapplication.model.CourseApplication
import org.springframework.data.jpa.repository.JpaRepository

interface CourseApplicationRepository : JpaRepository<CourseApplication, Long> {
    fun existsByCourseIdAndUserId(courseId: Long, userId: Long): Boolean
    fun findByCourseIdAndId(courseId: Long, applicationId: Long): CourseApplication?
}
