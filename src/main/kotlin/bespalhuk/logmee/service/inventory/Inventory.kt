package bespalhuk.logmee.service.inventory

data class Inventory(
    val id: String? = null,
    val idUser: String,
    val items: MutableList<String> = mutableListOf(),
)
