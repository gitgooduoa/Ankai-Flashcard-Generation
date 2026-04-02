package dev.apollointhehouse.models

import kotlinx.serialization.Serializable

@Serializable
data class Choice(
    val message: Message
)