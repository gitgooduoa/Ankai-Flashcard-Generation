package dev.apollointhehouse.models

import kotlinx.serialization.Serializable

@Serializable
data class Message(
    val role: String? = null,
    val content: String
)