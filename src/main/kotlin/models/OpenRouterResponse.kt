package dev.apollointhehouse.models

import kotlinx.serialization.Serializable

@Serializable
data class OpenRouterResponse(
    val choices: List<Choice>
)