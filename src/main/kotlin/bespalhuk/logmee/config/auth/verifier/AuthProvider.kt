package bespalhuk.logmee.config.auth.verifier

interface AuthProvider {
    fun identify(token: String): String
}
