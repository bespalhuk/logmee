package bespalhuk.logmee.service.inventory

data class InventoryOutput(
    val username: String,
    val items: List<String> = emptyList(),
)
