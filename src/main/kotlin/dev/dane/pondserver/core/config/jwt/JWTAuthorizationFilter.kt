package dev.dane.pondserver.core.config.jwt

import io.jsonwebtoken.Jwts
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import org.springframework.util.StringUtils
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthorizationFilter(authenticationManager: AuthenticationManager)
    : BasicAuthenticationFilter(authenticationManager) {

    val log = LoggerFactory.getLogger(this.javaClass)

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        super.doFilterInternal(request, response, chain)
    }

    fun getAuthentication(request: HttpServletRequest)
            : UsernamePasswordAuthenticationToken? {
        val token = request.getHeader(JWTConstants.TOKEN_HEADER)
        if(StringUtils.isEmpty(token) || !token.startsWith(JWTConstants.TOKEN_PREFIX)) {
            return null
        }

        try {
            val signingKey = JWTConstants.JWT_SECRET.toByteArray()
            val parser = Jwts.parserBuilder().setSigningKey(signingKey).build()
            val parsedToken = parser.parseClaimsJws(token)
            val username = parsedToken.body.subject
            if(!StringUtils.isEmpty(username)) {
                return UsernamePasswordAuthenticationToken(username, null)
            }
        } catch (e : Exception) {
            log.warn("Fail to parse JWT token")
        }

        return null
    }
}