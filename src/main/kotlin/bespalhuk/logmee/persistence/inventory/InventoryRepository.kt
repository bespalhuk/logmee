package bespalhuk.logmee.persistence.inventory

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface InventoryRepository : MongoRepository<InventoryDocument, String> {
    fun findByIdUser(idUser: String): InventoryDocument?
}
