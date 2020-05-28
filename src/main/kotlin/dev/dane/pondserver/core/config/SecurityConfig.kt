package dev.dane.pondserver.core.config

import dev.dane.pondserver.core.config.jwt.JWTAuthenticationFilter
import dev.dane.pondserver.core.config.jwt.JWTAuthorizationFilter
import dev.dane.pondserver.user.CustomUserDetailsService
import dev.dane.pondserver.user.UserService
import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
class SecurityConfig (val userService : UserService, val customUserDetailsService: CustomUserDetailsService) : WebSecurityConfigurerAdapter() {

    override fun configure(http : HttpSecurity) {
        http.cors()
            .and()
                .httpBasic().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/beat", "/api/auth").permitAll()
                .anyRequest().authenticated()
            .and()
                .addFilter(JWTAuthenticationFilter(authenticationManager()))
                .addFilter(JWTAuthorizationFilter(authenticationManager(), this.userService))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }

    override fun configure(authBuilder: AuthenticationManagerBuilder) {
        authBuilder.authenticationProvider(authProvider())
    }

    @Bean
    fun corsConfigurationSource() : CorsConfigurationSource {
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", CorsConfiguration().applyPermitDefaultValues())
        return source
    }

    @Bean
    fun authProvider() : DaoAuthenticationProvider {
        val daoAuthenticationProvider = DaoAuthenticationProvider()
        daoAuthenticationProvider.setUserDetailsService(this.customUserDetailsService)
        daoAuthenticationProvider.setPasswordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder())
        return daoAuthenticationProvider
    }
}