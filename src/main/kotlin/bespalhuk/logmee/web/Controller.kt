package bespalhuk.logmee.web

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller {

    @GetMapping("ae")
    fun ae(): String = "oe"
}
