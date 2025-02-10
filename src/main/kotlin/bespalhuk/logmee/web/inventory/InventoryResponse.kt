package bespalhuk.logmee.web.inventory

data class InventoryResponse(
    val username: String,
    val items: List<String>,
)
