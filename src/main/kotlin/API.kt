package dev.apollointhehouse

import com.google.genai.types.GenerateContentResponse
import dev.apollointhehouse.models.GeminiResponse
import dev.apollointhehouse.models.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runInterruptible
import kotlinx.serialization.json.Json
import models.Flashcard

object API {
    private val apiKey = System.getenv("GEMINI_KEY")
    private val client = Client {
        apiKey(apiKey)
    }

    suspend fun genRequest(note: Note): List<Flashcard> = runInterruptible(Dispatchers.IO) {
        val response: GenerateContentResponse =
            client.models.generateContent(
                model = "gemini-2.5-flash",
                text = "Title: ${note.title}\nContent: ${note.content}",
                config = GenerateContentConfig {
                    systemInstruction {
                        role("system")
                        parts(
                            Part {
                                text("You are a flashcard generator. Given a note title and content, generate an array of flashcards. Each flashcard should be on one part of the note content, try to keep it concise. Either side of each card should act as both a question and answer for the other side.")
                            }
                        )
                    }

                    responseMimeType("application/json")
                    responseSchema(Schema {
                        type("array")
                        items(Schema {
                            type("object")
                            properties(
                                mapOf(
                                    "front" to Schema { type("string") },
                                    "back" to Schema { type("string") },
                                )
                            )
                        })
                    })
                }
            )

        val json = response.text()
            ?.substringAfter("```json")
            ?.substringBefore("```")
            ?: throw IllegalStateException("Unable to generate content for note ${note.title}")
        val geminiResponse: List<GeminiResponse> = Json.decodeFromString(json)

        geminiResponse.map { Flashcard(it.front, it.back) }
    }
}