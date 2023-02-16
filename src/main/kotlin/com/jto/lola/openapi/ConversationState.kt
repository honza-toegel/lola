package com.jto.lola.openapi

import java.util.*

data class ConversationState(
    val conversationSpecification: ConversationSpecification,
    val conversationMessages: List<ConversationMessage>
) : AiPrompt {
    override fun formatToAiPrompt(locale: Locale, stopSequence: String): String {
        return "${conversationSpecification.formatToAiPrompt(locale, stopSequence)}$stopSequence" +
                "${
                    conversationMessages.joinToString(stopSequence) { message ->
                        message.formatToAiPrompt(
                            locale,
                            stopSequence
                        )
                    }
                }$stopSequence${conversationSpecification.chatBootName}:"
    }
}