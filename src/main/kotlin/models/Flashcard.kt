package dev.apollointhehouse.models

import kotlinx.serialization.Serializable

@Serializable
data class Flashcard(
    val title: String,
    val question: String,
    val answer: String
)
