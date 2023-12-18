package com.teamsparta.courseregistration

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class CourseRegistrationApplication

fun main(args: Array<String>) {
    runApplication<CourseRegistrationApplication>(*args)
}
