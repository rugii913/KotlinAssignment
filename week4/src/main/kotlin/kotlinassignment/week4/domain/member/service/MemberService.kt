package kotlinassignment.week4.domain.member.service

import kotlinassignment.week4.domain.member.dto.MemberLoginRequest
import kotlinassignment.week4.domain.member.dto.MemberLoginResponse
import kotlinassignment.week4.domain.member.dto.MemberSignUpRequest
import kotlinassignment.week4.domain.member.model.Member
import kotlinassignment.week4.domain.member.repository.MemberRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val passwordEncoder: PasswordEncoder,
) {

    fun login(request: MemberLoginRequest): MemberLoginResponse {
        return MemberLoginResponse(TODO())
    }

    @Transactional
    fun signUp(request: MemberSignUpRequest): Unit {
        if (memberRepository.existsByEmail(request.email)) {
            throw IllegalStateException("Email is already in use")
        }

        memberRepository.save(
            Member(
                email = request.email,
                password = passwordEncoder.encode(request.password),
            )
        )
    }
}
