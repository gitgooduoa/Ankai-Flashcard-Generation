package dev.apollointhehouse

import com.google.genai.Client
import com.google.genai.Models
import com.google.genai.types.Content
import com.google.genai.types.GenerateContentConfig
import com.google.genai.types.GenerateContentResponse
import com.google.genai.types.Part
import com.google.genai.types.Schema


inline fun Client(block: Client.Builder.() -> Unit): Client =
    Client.builder().apply(block).build()

fun Models.generateContent(
    model: String,
    text: String,
    config: GenerateContentConfig,
): GenerateContentResponse = generateContent(model, text, config)

inline fun GenerateContentConfig(
    block: GenerateContentConfig.Builder.() -> Unit
): GenerateContentConfig =
    GenerateContentConfig.builder().apply(block).build()

inline fun GenerateContentConfig.Builder.systemInstruction(
    block: Content.Builder.() -> Unit
): GenerateContentConfig.Builder =
    systemInstruction(Content(block))

inline fun Content(block: Content.Builder.() -> Unit): Content =
    Content.builder().apply(block).build()

inline fun Part(block: Part.Builder.() -> Unit): Part =
    Part.builder().apply(block).build()

inline fun Schema(block: Schema.Builder.() -> Unit): Schema =
    Schema.builder().apply(block).build()