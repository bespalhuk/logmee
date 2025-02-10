package bespalhuk.logmee.config.firebase

import com.google.firebase.auth.FirebaseAuth
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class FirebaseAuthFilter : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        val url = request.requestURI
        if (url.startsWith("/api")) {
            val authorizationHeader = request.getHeader("Authorization")

            if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing or invalid Authorization header.")
                return
            }

            val token = authorizationHeader.substring(7)
            try {
                val verifiedToken = FirebaseAuth.getInstance().verifyIdToken(token)
                val authToken = UsernamePasswordAuthenticationToken(verifiedToken.uid, null, emptyList())
                authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authToken
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
