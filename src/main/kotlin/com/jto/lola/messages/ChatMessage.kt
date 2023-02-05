package com.jto.lola.messages

import java.time.ZonedDateTime

data class ChatMessage(
    val id: Long,
    val sender: String,
    val originalText: String,
    val translatedText: String?,
    val isIncoming: Boolean,
    val timestamp: ZonedDateTime
)


