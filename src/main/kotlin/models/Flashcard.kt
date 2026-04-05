package models

import kotlinx.serialization.Serializable

@Serializable
data class Flashcard(
    val front: String,
    val back: String,
)
