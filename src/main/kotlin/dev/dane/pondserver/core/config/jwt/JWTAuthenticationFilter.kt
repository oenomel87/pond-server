package dev.dane.pondserver.core.config.jwt

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthenticationFilter(authenticationManager: AuthenticationManager)
    : UsernamePasswordAuthenticationFilter() {

    init {
        this.authenticationManager = authenticationManager
        setFilterProcessesUrl(JWTConstants.AUTH_PATH)
    }

    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        val username = request?.getParameter("username")
        val password = request?.getParameter("password")
        val authenticationToken = UsernamePasswordAuthenticationToken(username, password)
        return authenticationManager.authenticate(authenticationToken)
    }

    override fun successfulAuthentication(request: HttpServletRequest?, response: HttpServletResponse?, chain: FilterChain?, authResult: Authentication?) {
        val username = authResult?.principal as String?
        val signingKey = JWTConstants.JWT_SECRET.toByteArray()
        val token = Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
                .setHeaderParam("typ", JWTConstants.TOKEN_TYPE)
                .setIssuer(JWTConstants.TOKEN_ISSUER)
                .setAudience(JWTConstants.TOKEN_AUDIENCE)
                .setSubject(username)
                .setExpiration(Date(System.currentTimeMillis() + 864000000))
                .compact()
        response?.setHeader(JWTConstants.TOKEN_HEADER, JWTConstants.TOKEN_PREFIX + token)
        response?.writer?.write(token)
    }
}