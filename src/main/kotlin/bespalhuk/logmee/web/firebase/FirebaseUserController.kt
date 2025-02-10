package bespalhuk.logmee.web.firebase

import bespalhuk.logmee.service.AuthenticationService
import bespalhuk.logmee.service.user.UserService
import bespalhuk.logmee.web.toInput
import bespalhuk.logmee.web.toResponse
import bespalhuk.logmee.web.user.UserRequest
import bespalhuk.logmee.web.user.UserResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class FirebaseUserController(
    private val authenticationService: AuthenticationService,
    private val userService: UserService,
) {

    @PostMapping
    fun create(
        @RequestBody request: UserRequest
    ): UserResponse {
        val idAuth = authenticationService.getAuthId()
        return userService.save(
            request.toInput(idAuth)
        ).toResponse()
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun find(
        @PathVariable("id") id: String
    ): UserResponse {
        val idAuth = authenticationService.getAuthId()
        return userService.find(
            id,
            idAuth,
        ).toResponse()
    }
}
