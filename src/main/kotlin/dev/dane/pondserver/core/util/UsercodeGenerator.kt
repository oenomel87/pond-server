package dev.dane.pondserver.core.util

import java.util.*

class UsercodeGenerator {

    fun generateUsercode () : String {
        val uuid = UUID.randomUUID()
        return uuid.toString().replace("-", "")
    }
}