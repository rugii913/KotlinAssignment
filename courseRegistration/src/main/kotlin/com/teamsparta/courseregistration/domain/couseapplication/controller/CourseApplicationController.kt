package com.teamsparta.courseregistration.domain.couseapplication.controller

import com.teamsparta.courseregistration.domain.course.service.CourseService
import com.teamsparta.courseregistration.domain.couseapplication.dto.ApplyCourseRequest
import com.teamsparta.courseregistration.domain.couseapplication.dto.CourseApplicationResponse
import com.teamsparta.courseregistration.domain.couseapplication.dto.UpdateApplicationStatusRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/courses/{courseId}/applications")
@RestController
class CourseApplicationController(
    private val courseService: CourseService
) {

    @GetMapping
    fun getApplicationList(@PathVariable courseId: Long): ResponseEntity<List<CourseApplicationResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(courseService.getCourseApplicationList(courseId))
    }

    @GetMapping("/{applicationId}")
    fun getApplication(
        @PathVariable courseId: Long,
        @PathVariable applicationId: Long,
    ): ResponseEntity<CourseApplicationResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(courseService.getCourseApplication(courseId, applicationId))
    }

    @PostMapping
    fun applyCourse(
        @PathVariable courseId: Long,
        applyCourseRequest: ApplyCourseRequest
    ): ResponseEntity<CourseApplicationResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(courseService.applyCourse(courseId, applyCourseRequest))
    }

    @PatchMapping("/{applicationId}")
    fun updateApplicationStatus(
        @PathVariable courseId: Long,
        @PathVariable applicationId: Long,
        @RequestBody updateApplicationStatusRequest: UpdateApplicationStatusRequest,
    ): ResponseEntity<CourseApplicationResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(
                courseService.updateCourseApplicationStatus(
                    courseId,
                    applicationId,
                    updateApplicationStatusRequest
                )
            )
    }
}
