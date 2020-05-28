package dev.dane.pondserver.core.config.jwt

import dev.dane.pondserver.user.UserService
import dev.dane.pondserver.user.domain.entity.UserPrincipal
import io.jsonwebtoken.Jwts
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import org.springframework.util.StringUtils
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthorizationFilter(authenticationManager: AuthenticationManager, val userService : UserService)
    : BasicAuthenticationFilter(authenticationManager) {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val authentication = this.getAuthentication(request)
        if(authentication != null) {
            SecurityContextHolder.getContext().authentication = authentication
        }
        chain.doFilter(request, response)
    }

    private fun getAuthentication(request: HttpServletRequest)
            : UsernamePasswordAuthenticationToken? {
        val token = request.getHeader(JWTConstants.TOKEN_HEADER)
        if(StringUtils.isEmpty(token) || !token.startsWith(JWTConstants.TOKEN_PREFIX)) {
            return null
        }

        try {
            val signingKey = JWTConstants.JWT_SECRET.toByteArray()
            val tokenBody = token.replace(JWTConstants.TOKEN_PREFIX, "")
            val parser = Jwts.parserBuilder().setSigningKey(signingKey).build()
            val parsedToken = parser.parseClaimsJws(tokenBody)
            val username = parsedToken.body.subject
            if(!StringUtils.isEmpty(username)) {
                val user = this.userService.fetchUser(username) ?: return null
                val principal = UserPrincipal(user)
                return UsernamePasswordAuthenticationToken(username, null, principal.authorities)
            }
        } catch (e : Exception) {
            log.warn("Fail to parse JWT token")
        }

        return null
    }
}