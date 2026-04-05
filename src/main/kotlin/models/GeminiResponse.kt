package dev.apollointhehouse.models

import kotlinx.serialization.Serializable

@Serializable
class GeminiResponse(
    val front: String,
    val back: String,
)