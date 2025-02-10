package bespalhuk.logmee.service.inventory

import bespalhuk.logmee.persistence.inventory.InventoryRepository
import bespalhuk.logmee.persistence.toDocument
import bespalhuk.logmee.persistence.toDomain
import bespalhuk.logmee.persistence.user.UserRepository
import bespalhuk.logmee.service.exception.NotFoundException
import bespalhuk.logmee.service.user.User
import org.springframework.stereotype.Service

@Service
class InventoryService(
    private val userRepository: UserRepository,
    private val inventoryRepository: InventoryRepository,
) {

    fun add(input: InventoryInput): InventoryOutput {
        val user = findUser(input.idUser, input.idAuth)

        val inventory = inventoryRepository.findByIdUser(user.id!!)
            ?.toDomain()
            ?: Inventory(
                idUser = user.id,
            )

        inventory.apply {
            items.addAll(input.items)
        }

        val saved = inventoryRepository.save(inventory.toDocument()).toDomain()

        return InventoryOutput(
            user.username,
            saved.items,
        )
    }

    fun find(idUser: String, idAuth: String): InventoryOutput {
        val user = findUser(idUser, idAuth)

        val inventory = inventoryRepository.findByIdUser(user.id!!)
            ?.toDomain() ?: throw NotFoundException("Inventory not found.")

        return InventoryOutput(
            user.username,
            inventory.items,
        )
    }

    private fun findUser(idUser: String, idAuth: String): User =
        userRepository.findByIdAndIdAuth(idUser, idAuth)
            ?.toDomain() ?: throw NotFoundException("User not found.")
}
