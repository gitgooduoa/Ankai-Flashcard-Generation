package dev.apollointhehouse

import dev.apollointhehouse.database.FlashcardService
import dev.apollointhehouse.models.Note
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.request.receive
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting(flashcardService: FlashcardService) {
    routing {
        post("/api/flashcards") {
            val note = call.receive<Note>()
            val existing = flashcardService.findByNote(note.title, note.content)
            if (existing != null) {
                call.respond(HttpStatusCode.OK, existing)
                return@post
            }

            try {
                val flashcard = API.genRequest(note)
                flashcardService.create(flashcard)
                call.respond(HttpStatusCode.Created, flashcard)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, "Failed to generate flashcard: ${e.message}")
            }
        }
    }
}
