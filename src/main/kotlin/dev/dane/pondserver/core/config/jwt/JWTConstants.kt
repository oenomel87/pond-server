package dev.dane.pondserver.core.config.jwt

object JWTConstants {

    const val AUTH_PATH : String = "/auth"

    const val JWT_SECRET : String = "VkYp3s6v8y/B?E(H+MbQeThWmZq4t7w!z\$C&F)J@NcRfUjXn2r5u8x/A?D*G-KaP"

    const val TOKEN_HEADER : String = "Authorization"

    const val TOKEN_PREFIX : String = "Bearer "

    const val TOKEN_TYPE : String = "JWT"

    const val TOKEN_ISSUER :  String = "pond-server"

    const val TOKEN_AUDIENCE : String = "pond-client"
}
