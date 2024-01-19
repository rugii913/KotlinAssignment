package com.teamsparta.courseregistration.domain.course.repository

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.Expression
import com.querydsl.core.types.Order
import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.dsl.EntityPathBase
import com.querydsl.core.types.dsl.PathBuilder
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
        return findByPageableAndStatusV2(pageable, courseStatus) // 리팩토링 과정을 보여주기 위해 이렇게 구성함
    }

    private fun findByPageableAndStatusV2(pageable: Pageable, courseStatus: CourseStatus?): Page<Course> {
        val whereClause = BooleanBuilder()
        courseStatus?.let { whereClause.and(course.status.eq(courseStatus)) }

        val totalCount = queryFactory.select(course.count()).from(course).where(whereClause).fetchOne() ?: 0L

        val contents = queryFactory.selectFrom(course)
            .where(whereClause)
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .orderBy(*getOrderSpecifierV2(pageable, course))
            .fetch()

        return PageImpl(contents, pageable, totalCount)
    }

    private fun getOrderSpecifierV2(pageable: Pageable, path: EntityPathBase<*>): Array<OrderSpecifier<*>> {
        // QClass들은 모두 EntityPathBase<해당 Class>를 상속하는 형태로 되어있다. - 이를 이용해서 여러 repository에서 사용할 수 있는 유틸리티처럼 만들어볼 수도 있다.
        val pathBuilder = PathBuilder(path.type, path.metadata)

        return pageable.sort.toList().map { order ->
            OrderSpecifier(
                if (order.isAscending) Order.ASC else Order.DESC,
                pathBuilder.get(order.property) as Expression<Comparable<*>>,
            )
        }.toTypedArray()
    }

    private fun findByPageableAndStatusV1(pageable: Pageable, courseStatus: CourseStatus?): Page<Course> {
        val whereClause = BooleanBuilder()
        courseStatus?.let { whereClause.and(course.status.eq(courseStatus)) }

        val totalCount = queryFactory.select(course.count()).from(course).where(whereClause).fetchOne() ?: 0L

        val contents = queryFactory.selectFrom(course)
            .where(whereClause)
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .orderBy(*getOrderSpecifierV1(pageable)) // Array 타입 객체 앞에 *을 붙이면 가변 인자로 풀어줄 수 있다.
            .fetch()

        return PageImpl(contents, pageable, totalCount)
    }

    private fun getOrderSpecifierV1(pageable: Pageable): Array<OrderSpecifier<*>> {
        val pathBuilder = PathBuilder(course.type, course.metadata) // PathBuilder에 type과 metadata를 넘기면 그 type의 path들을 추출할 수 있음

        // pageable.sort는 Sort 타입을 return, Sort 타입은 Streamable<Order>의 구현체로 안에 List<Order> orders를 갖고 있음
        // (cf.) Streamable은 Spring framework에서 제공하는 util 클래스
        // array로 바꾸기 편하도록 Stream 기반으로 map 하지 않고 Streamable 인터페이스 이용하여 toList로 List<Order>로 바꾼 뒤 map
        // List의 각 Order 타입을 OrderSpecifier로 만들어 준다.
        return pageable.sort.toList().map { order ->
            OrderSpecifier(
            // Order 타입을 까보면 각 Order는 property와 direction 같은 정보들을 갖고 있다.
            //  - 이를 이용해서 Spring Data의 Order 타입을 QueryDSL의 Order 타입으로 바꿔줌
                if (order.isAscending) Order.ASC else Order.DESC, // -> pageable 내의 order 정보를 바탕으로 QueryDSL Order를 넘김
            // 여기서 Expression은 course.id, course.title 같은 것들을 말함
            //  - 이들은 Path이므로 PathBuilder를 이용해준다.
                pathBuilder.get(order.property) as Expression<Comparable<*>>,
            // (cf.) Kotlin으로 작성하고 있어서, Java가 알아들을 수 있게 타입 캐스팅
            //  - OrderSpecifier의 제네릭이 <T extends Comparable>이고 Expression의 제네릭이 Expression<T>이므로 Expression<Comparable<*>>로 캐스팅한다.
            //  - 다이어그램을 까보면 querydsl.core 패키지에서 Expression은 PathBuilde의 상위 타입이다.
            // order에 있는 property는 "id", "title" 같은 query parameter String으로 들어온 정렬 기준 정보
            //  -> pathBuilder.get(~)은 이 Course에 있는 프로퍼티 이름 String을 받아서 이에 맞는 PathBuilder를 반환한다.
            )
        }.toTypedArray()
    }

    private fun findByPageableAndStatusV0(pageable: Pageable, courseStatus: CourseStatus?): Page<Course> {
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
