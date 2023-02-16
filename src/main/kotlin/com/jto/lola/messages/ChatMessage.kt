package com.jto.lola.messages

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.ZonedDateTime
import java.util.*

data class ChatMessage(
    val clientMessageId: UUID,
    val senderUserId: String,
    val originalText: String,
    val translatedText: String?,

    //Must be set based on userId dynamically
    val isIncoming: Boolean,
    //2023-02-08T06:12:16+00:00
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    val timestamp: ZonedDateTime
) {
    companion object {
        fun fromChatMessageRequest(chatMessageRequest: ChatMessageRequest, senderUserId: String): ChatMessage =
            ChatMessage(chatMessageRequest.clientMessageId, senderUserId, chatMessageRequest.originalText, "translated: ${chatMessageRequest.originalText}", false, chatMessageRequest.timestamp)
    }
}


