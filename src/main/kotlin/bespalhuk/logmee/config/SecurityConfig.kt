package bespalhuk.logmee.config

import bespalhuk.logmee.config.firebase.FirebaseAuthFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
class SecurityConfig(val firebaseAuthFilter: FirebaseAuthFilter) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf { it -> it.disable() }
        http.formLogin { it -> it.disable() }
        http.httpBasic { it -> it.disable() }

        http.authorizeHttpRequests { requests ->
            requests.requestMatchers("/api/**").authenticated()
            requests.anyRequest().permitAll()
        }

        http.addFilterBefore(firebaseAuthFilter, UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }
}
