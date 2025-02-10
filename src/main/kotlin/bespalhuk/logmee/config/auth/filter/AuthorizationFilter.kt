package bespalhuk.logmee.config.auth.filter

import bespalhuk.logmee.config.auth.verifier.FirebaseAuthProvider
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import mu.KotlinLogging
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

private val logger = KotlinLogging.logger {}

@Component
class AuthorizationFilter(
    firebaseAuthProvider: FirebaseAuthProvider,
) : OncePerRequestFilter() {

    private val authorizationProviders = mapOf(
        "firebase" to firebaseAuthProvider,
    )

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        val url = request.requestURI
        if (url.startsWith("/api")) {
            val authorizationProviderHeader = request.getHeader("Authorization-Provider")
            if (authorizationProviderHeader == null || authorizationProviderHeader.trim().isEmpty()) {
                val message = "Missing Authorization-Provider header."
                logger.error(message)
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, message)
                return
            }

            val authorizationProvider = authorizationProviders[authorizationProviderHeader]
            if (authorizationProvider == null) {
                val message = "Invalid Authorization-Provider header."
                logger.error(message)
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, message)
                return
            }

            val authorizationHeader = request.getHeader("Authorization")
            if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
                val message = "Missing or invalid Authorization header."
                logger.error(message)
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, message)
                return
            }

            try {
                val identified = authorizationProvider.identify(authorizationHeader.substring(7))
                val authentication = UsernamePasswordAuthenticationToken(identified, null, emptyList())
                authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authentication
            } catch (exception: Exception) {
                response.status = HttpServletResponse.SC_UNAUTHORIZED
                response.contentType = "application/json"
                response.writer.write("Invalid or expired token.")
                return
            }
        }

        filterChain.doFilter(request, response)
    }
}
