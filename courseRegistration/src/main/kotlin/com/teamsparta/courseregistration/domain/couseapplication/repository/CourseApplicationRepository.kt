package com.teamsparta.courseregistration.domain.couseapplication.repository

import com.teamsparta.courseregistration.domain.couseapplication.model.CourseApplication
import org.springframework.data.jpa.repository.JpaRepository

interface CourseApplicationRepository : JpaRepository<CourseApplication, Long>
