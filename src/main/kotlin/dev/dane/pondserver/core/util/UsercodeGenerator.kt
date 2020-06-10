package dev.dane.pondserver.core.util

import java.util.*

fun generateUsercode () : String {
    val uuid = UUID.randomUUID()
    return uuid.toString().replace("-", "")
}