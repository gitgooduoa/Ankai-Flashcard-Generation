package dev.apollointhehouse.models

import kotlinx.serialization.Serializable

@Serializable
data class OpenRouterRequest(
    val messages: List<Message>,
)