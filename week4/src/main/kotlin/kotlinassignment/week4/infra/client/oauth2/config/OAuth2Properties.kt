package kotlinassignment.week4.infra.client.oauth2.config

/* https://velog.io/@max9106/OAuth4 참고 */
data class OAuth2Properties(
    val oAuth2Provider: OAuth2Provider,
    val authBaseUri: String,
    val clientId: String,
    val redirectUri: String,
    val apiBaseUri: String,
)
