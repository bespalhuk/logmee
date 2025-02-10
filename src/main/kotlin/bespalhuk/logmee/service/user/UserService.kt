package bespalhuk.logmee.service.user

import bespalhuk.logmee.persistence.toDocument
import bespalhuk.logmee.persistence.toDomain
import bespalhuk.logmee.persistence.user.UserRepository
import bespalhuk.logmee.service.exception.NotFoundException
import bespalhuk.logmee.service.exception.UserAlreadyExistsException
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
) {

    fun save(input: UserInput): User {
        if (userRepository.existsByUsername(input.username)) {
            throw UserAlreadyExistsException()
        }

        val user = User(
            idAuth = input.idAuth,
            username = input.username,
        )

        return userRepository.save(
            user.toDocument(),
        ).toDomain()
    }

    fun find(id: String, idAuth: String): User {
        val user = userRepository.findByIdAndIdAuth(id, idAuth)
        return user?.toDomain() ?: throw NotFoundException("User not found.")
    }
}
