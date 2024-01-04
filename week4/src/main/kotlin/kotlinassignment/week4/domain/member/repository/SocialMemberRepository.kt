package kotlinassignment.week4.domain.member.repository

import kotlinassignment.week4.domain.member.model.SocialMember
import org.springframework.data.jpa.repository.JpaRepository

/* 챌린지반 강의 코드 가져와서 일부 수정 */
interface SocialMemberRepository : JpaRepository<SocialMember, Long>
