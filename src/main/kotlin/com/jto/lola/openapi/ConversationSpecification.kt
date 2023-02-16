package com.jto.lola.openapi

import java.util.*

data class ConversationSpecification(
    val topic: String?,
    val chatBootMoods: List<String>,
    val chatBootName: String,
    val userName: String,
    val conversationPartnerDescription: String,
) : AiPrompt {
    val isChatBootProactive: Boolean = chatBootMoods.any { characteristic -> characteristic.contains("proactive") }

    override fun formatToAiPrompt(locale: Locale, stopSequence: String): String {
        val topicSpec = if (!topic.isNullOrBlank())
            "about $topic"
        else
            ""
        var aiPrompt =
            "This is a conversation $topicSpec between AI called '$chatBootName' and '$userName'. $userName is $conversationPartnerDescription."
        if (chatBootMoods.isNotEmpty())
            aiPrompt += " The $chatBootName is ${chatBootMoods.joinToString()}."
        return aiPrompt
    }
}
