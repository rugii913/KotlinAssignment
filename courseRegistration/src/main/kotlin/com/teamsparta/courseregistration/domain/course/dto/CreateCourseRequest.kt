package com.teamsparta.courseregistration.domain.course.dto

data class CreateCourseRequest( // 생성할 때는 id가 없다. // title, description 외에는 정책으로 이미 결정되어 있거나, 기본값을 주면 될 상태들
    val title: String,
    val description: String?,
)
