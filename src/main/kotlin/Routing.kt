package dev.apollointhehouse

import dev.apollointhehouse.models.Note
import io.github.oshai.kotlinlogging.KotlinLogging
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

private val logger = KotlinLogging.logger {}

fun Application.configureRouting() {
    routing {
        post("/api/flashcards") {
            val note = call.receive<Note>()

            try {
                val flashcards = API.genRequest(note)
                logger.info { flashcards }
                call.respond(flashcards)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, "Failed to generate flashcards: ${e.message}")
            }
        }
    }
}
