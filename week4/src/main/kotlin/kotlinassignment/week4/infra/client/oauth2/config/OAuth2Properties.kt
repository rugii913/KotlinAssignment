package kotlinassignment.week4.infra.client.oauth2.config

data class OAuth2Properties(
    val baseUri: String,
    val clientId: String,
    val redirectUri: String,
)