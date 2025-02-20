package dev.aga.config

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.filter.OncePerRequestFilter

@Configuration
@EnableWebSecurity
class SecurityConfig {

  @Bean
  fun filterChain(http: HttpSecurity): SecurityFilterChain {
    return http.csrf { it.disable() }
      .cors(Customizer.withDefaults())
      .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
      .exceptionHandling {
        it.authenticationEntryPoint { _, rsp, _ ->
          rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED)
        }
      }.authorizeHttpRequests { it.anyRequest().permitAll() }
      // if you uncomment the filter, the test starts failing
      //.addFilterAfter(LoggingFilter, UsernamePasswordAuthenticationFilter::class.java)
      .build()
  }

  object LoggingFilter : OncePerRequestFilter() {
    private val logger = LoggerFactory.getLogger(LoggingFilter::class.java)
    override fun doFilterInternal(
      request: HttpServletRequest,
      response: HttpServletResponse,
      filterChain: FilterChain
    ) {
      logger.info("foo")
    }
  }
}
