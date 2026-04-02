package dev.apollointhehouse

import dev.apollointhehouse.models.Flashcard
import dev.apollointhehouse.models.Message
import dev.apollointhehouse.models.Note
import dev.apollointhehouse.models.OpenRouterRequest
import dev.apollointhehouse.models.OpenRouterResponse
import dev.apollointhehouse.models.ResponseFormat
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.bodyAsText
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

private const val OPEN_ROUTER = "https://openrouter.ai/api/v1/chat/completions"

object API {
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    private val apiKey = System.getenv("OPEN_ROUTER")

    suspend fun genRequest(note: Note): Flashcard {
        val response: OpenRouterResponse = client.post(OPEN_ROUTER) {
            header(HttpHeaders.Authorization, "Bearer $apiKey")
            contentType(ContentType.Application.Json)
            setBody(
                OpenRouterRequest(
                    messages = listOf(
                        Message(
                            role = "system",
                            content = "You are a flashcard generator. Given a note title and content, generate a flashcard answer. Return only a JSON object with 'title', 'question', and 'answer' fields. The question should be the note content, and the answer should be a concise explanation or summary."
                        ),
                        Message(
                            role = "user",
                            content = "Title: ${note.title}\nContent: ${note.content}"
                        )
                    ),
                )
            )
        }.also { println(it.bodyAsText()) }.body()

        val content = response.choices.firstOrNull()?.message?.content ?: throw Exception("Failed to generate flashcard")
        return Json.decodeFromString<Flashcard>(content)
    }
}