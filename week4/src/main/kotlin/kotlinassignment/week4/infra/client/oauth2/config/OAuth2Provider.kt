package kotlinassignment.week4.infra.client.oauth2.config

/* 챌린지반 강의 코드 가져와서 일부 수정 */
enum class OAuth2Provider(val uriPostfix: String) {
    KAKAO("kakao"), NAVER("naver")
}
