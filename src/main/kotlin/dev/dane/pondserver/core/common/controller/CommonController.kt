package dev.dane.pondserver.core.common.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CommonController {

    @GetMapping("", "/")
    fun root() : String {
        return "Ok"
    }

    @GetMapping("/beat")
    fun healthCheck() : String {
        return "It works"
    }
}