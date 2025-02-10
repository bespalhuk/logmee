package bespalhuk.logmee.service

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class AuthService {

    fun getAuthId() = SecurityContextHolder.getContext().authentication.principal as String
}
