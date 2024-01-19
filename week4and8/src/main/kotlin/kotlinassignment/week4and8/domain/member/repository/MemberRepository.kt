package kotlinassignment.week4and8.domain.member.repository

import kotlinassignment.week4and8.domain.member.model.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository: JpaRepository<Member, Long> {

    fun existsByEmail(email: String): Boolean

    fun findByEmail(email: String): Member?
}
