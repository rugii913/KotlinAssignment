package com.teamsparta.courseregistration.infra.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component

@Aspect
@Component
class TestAop { // aspect를 작성 - pointcut과 advice 정보를 갖고 있는 하나의 객체가 된다 - 즉 부가기능이 모듈화

    @Around("execution(* com.teamsparta.courseregistration.domain.course.service.CourseService.getCourseById(..))")
    fun thisIsAdvice(joinPoint: ProceedingJoinPoint) {
        println("AOP START!!!")
        joinPoint.proceed()
        println("AOP END!!!")
    }
}
