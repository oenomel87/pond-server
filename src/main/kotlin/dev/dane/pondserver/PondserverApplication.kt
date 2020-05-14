package dev.dane.pondserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PondserverApplication

fun main(args: Array<String>) {
    runApplication<PondserverApplication>(*args)
}
