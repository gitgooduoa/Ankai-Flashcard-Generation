package dev.apollointhehouse.models

import kotlinx.serialization.Serializable

@Serializable
class Note(
    val title: String,
    val content: String,
)