package com.jto.lola.openapi

import java.util.*

data class ConversationMessage(val userName: String, val message: String) : AiPrompt {
    override fun formatToAiPrompt(locale: Locale, stopSequence: String): String {
        return "$userName: $message"
    }
}
