package bespalhuk.logmee.service.inventory

import java.time.Instant

data class Inventory(
    val id: String? = null,
    val createdDate: Instant? = null,
    val lastModified: Instant? = null,
    val idUser: String,
    val items: MutableList<String> = mutableListOf(),
)
