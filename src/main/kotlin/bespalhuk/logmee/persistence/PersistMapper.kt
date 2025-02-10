package bespalhuk.logmee.persistence

import bespalhuk.logmee.persistence.inventory.InventoryDocument
import bespalhuk.logmee.persistence.user.UserDocument
import bespalhuk.logmee.service.inventory.Inventory
import bespalhuk.logmee.service.user.User

fun User.toDocument(
) = UserDocument(
    id = id,
    idAuth = idAuth,
    username = username,
)

fun UserDocument.toDomain(
) = User(
    id = id,
    idAuth = idAuth,
    username = username,
)

fun Inventory.toDocument(
) = InventoryDocument(
    id = id,
    idUser = idUser,
    items = items,
)

fun InventoryDocument.toDomain(
) = Inventory(
    id = id,
    idUser = idUser,
    items = items,
)
