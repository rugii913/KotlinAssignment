package kotlinassignment.week4.domain.member.controller

import kotlinassignment.week4.domain.member.dto.MemberSignUpRequest
import kotlinassignment.week4.domain.member.service.MemberService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberController(
    private val memberService: MemberService
) {

    @PostMapping("/signup")
    fun signUp(@RequestBody request: MemberSignUpRequest): ResponseEntity<Unit> {
        // TODO 일단 최대한 단순한 형태로 구현함, response 내용도 따로 주지 않고, 성공 실패 여부만 응답
        return memberService.signUp(request)
            .let { ResponseEntity.status(HttpStatus.CREATED).build() }
    }
}