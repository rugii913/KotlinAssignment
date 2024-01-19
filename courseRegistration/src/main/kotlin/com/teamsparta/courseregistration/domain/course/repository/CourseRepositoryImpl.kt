package com.teamsparta.courseregistration.domain.course.repository

import com.querydsl.core.BooleanBuilder
import com.teamsparta.courseregistration.domain.course.dto.CourseResponse
import com.teamsparta.courseregistration.domain.course.model.Course
import com.teamsparta.courseregistration.domain.course.model.CourseStatus
import com.teamsparta.courseregistration.domain.course.model.QCourse
import com.teamsparta.courseregistration.infra.querydsl.QueryDslSupport
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

@Repository
class CourseRepositoryImpl: QueryDslSupport(), CustomCourseRepository {

    private val course = QCourse.course

    override fun searchCourseListByTitle(title: String): List<Course>? {
        return queryFactory.selectFrom(course)
            .where(course.title.containsIgnoreCase(title))
            .fetch()
    }

    override fun findByPageableAndStatus(pageable: Pageable, courseStatus: CourseStatus?): Page<Course> {
        // courseStatus가 null일 수 있으므로 이에 따라 동적 쿼리 작성 - 여기서는 BooleanBuilder 사용해서 whereClause란 변수에 저장
        val whereClause = BooleanBuilder()
        courseStatus?.let { whereClause.and(course.status.eq(courseStatus)) }
        
        // return할 때, PageImpl에 total도 넘겨야하므로 total을 세어옴 - count는 order와 무관하므로 바로 수행한다.
        val totalCount = queryFactory.select(course.count()).from(course).where(whereClause).fetchOne() ?: 0L

        // 얻어오려는 주된 정보인 course 목록을 가져오기 위한 쿼리 중 order가 아직 결정되지 않은 쿼리
        val query = queryFactory.selectFrom(course)
            .where(whereClause)
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
        
        // 위 query에 order도 동적으로 결정해서 붙임
        if (pageable.sort.isSorted) { // isSorted() - 이 pageable 객체에 sort가 있는지 없는지 확인
            // 각 정렬 기준마다 Order는 하나만 들어가있다고 가정하고, first()로 정렬 기준만 확인한다.
            when (pageable.sort.first()?.property) {
                "id" -> query.orderBy(course.id.asc())
                "title" -> query.orderBy(course.title.asc())
                else -> query.orderBy(course.id.asc())
            }
        } else {
            query.orderBy(course.id.asc())
        }

        // 파라미터와 조건문에 따라 동적으로 만들어진 쿼리 최종 수행
        val contents = query.fetch()

        // PageImpl에 필요한 정보들 넣어서 return
        return PageImpl(contents, pageable, totalCount)
    }
}
