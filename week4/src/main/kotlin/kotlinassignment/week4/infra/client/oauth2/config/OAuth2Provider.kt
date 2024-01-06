package kotlinassignment.week4.infra.client.oauth2.config

/* 챌린지반 강의 코드 가져와서 일부 수정 */
enum class OAuth2Provider(val uriSegment: String) {
    KAKAO("kakao"), NAVER("naver");

    fun getCorrespondingProperties(mapper: OAuth2ProviderPropertiesMapper): OAuth2Properties { // mapper는 서버가 뜨면서 동적으로 생성되므로 주입받아야 한다.
        return mapper.getOAuth2Properties(this)
    }
}
