package com.jto.lola.messages

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.ZonedDateTime
import java.util.*

data class ChatMessageRequest(
    val clientMessageId: UUID,
    //val senderUserId: String,
    val originalText: String,
    //2023-02-08T06:12:16+00:00
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    val timestamp: ZonedDateTime
)


