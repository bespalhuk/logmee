package bespalhuk.logmee.service.user

import java.time.Instant

data class User(
    val id: String? = null,
    val createdDate: Instant? = null,
    val lastModified: Instant? = null,
    val idAuth: String,
    val username: String,
)
