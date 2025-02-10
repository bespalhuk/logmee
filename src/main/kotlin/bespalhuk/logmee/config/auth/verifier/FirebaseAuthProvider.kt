package bespalhuk.logmee.config.auth.verifier

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseToken
import org.springframework.stereotype.Component

@Component
class FirebaseAuthProvider : AuthProvider {

    override fun identify(token: String): String {
        val verifiedToken = verifyToken(token)
        return verifiedToken.uid
    }

    private fun verifyToken(token: String): FirebaseToken =
        FirebaseAuth.getInstance().verifyIdToken(token)
}
