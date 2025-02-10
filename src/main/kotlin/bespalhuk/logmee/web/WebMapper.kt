package bespalhuk.logmee.web

import bespalhuk.logmee.service.inventory.InventoryInput
import bespalhuk.logmee.service.inventory.InventoryOutput
import bespalhuk.logmee.service.user.User
import bespalhuk.logmee.service.user.UserInput
import bespalhuk.logmee.web.inventory.InventoryRequest
import bespalhuk.logmee.web.inventory.InventoryResponse
import bespalhuk.logmee.web.user.UserRequest
import bespalhuk.logmee.web.user.UserResponse

fun UserRequest.toInput(
    idAuth: String,
) = UserInput(
    idAuth = idAuth,
    username = username,
)

fun User.toResponse(
) = UserResponse(
    id = id!!,
    username = username,
)

fun InventoryRequest.toInput(
    idUser: String,
    idAuth: String,
) = InventoryInput(
    idUser = idUser,
    idAuth = idAuth,
    items = items,
)

fun InventoryOutput.toResponse(
) = InventoryResponse(
    username = username,
    items = items,
)
