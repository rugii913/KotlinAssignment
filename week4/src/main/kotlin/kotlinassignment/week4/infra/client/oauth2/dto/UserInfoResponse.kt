package kotlinassignment.week4.infra.client.oauth2.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

/* 챌린지반 강의 코드 가져와서 수정 */
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class UserInfoResponse(
    val id: Long,
    val userPropertiesResponse: UserPropertiesResponse
) {
    val nickname: String
        get() = userPropertiesResponse.nickname
}
