package bespalhuk.logmee.persistence.user

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : MongoRepository<UserDocument, String> {
    fun existsByUsername(username: String): Boolean
    fun findByIdAndIdAuth(id: String, idAuth: String): UserDocument?
}
