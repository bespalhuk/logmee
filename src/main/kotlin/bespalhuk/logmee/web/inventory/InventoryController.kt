package bespalhuk.logmee.web.inventory

import bespalhuk.logmee.service.AuthenticationService
import bespalhuk.logmee.service.inventory.InventoryService
import bespalhuk.logmee.web.toInput
import bespalhuk.logmee.web.toResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class InventoryController(
    private val authenticationService: AuthenticationService,
    private val inventoryService: InventoryService,
) {

    @PostMapping("/{idUser}/inventory")
    fun add(
        @PathVariable("idUser") idUser: String,
        @RequestBody request: InventoryRequest
    ): InventoryResponse {
        val idAuth = authenticationService.getAuthId()
        return inventoryService.add(
            request.toInput(
                idUser,
                idAuth,
            )
        ).toResponse()
    }

    @GetMapping("/{idUser}/inventory")
    fun find(
        @PathVariable("idUser") idUser: String,
    ): InventoryResponse {
        val idAuth = authenticationService.getAuthId()
        return inventoryService.find(
            idUser,
            idAuth,
        ).toResponse()
    }
}
