package bespalhuk.logmee.service.inventory

data class InventoryInput(
    val idAuth: String,
    val idUser: String,
    val items: List<String>,
)
