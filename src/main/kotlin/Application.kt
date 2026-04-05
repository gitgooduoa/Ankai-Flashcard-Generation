package dev.apollointhehouse

import dev.apollointhehouse.plugins.configureHTTP
import dev.apollointhehouse.plugins.configureMonitoring
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.cio.EngineMain.main(args)
}

fun Application.module() {
    configureHTTP()
    configureMonitoring()
    configureRouting()
}
