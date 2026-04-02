package dev.apollointhehouse.models

import kotlinx.serialization.Serializable

@Serializable
data class ResponseFormat(
    val type: String
)