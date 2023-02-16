package com.jto.lola.openapi

import org.springframework.stereotype.Service
import java.util.*

@Service
class ChatBootService(private val completionService: CompletionService) {

    fun getChatBootReaction(
        conversationState: ConversationState
    ): ConversationMessage {
        val aiPrompt = formatAIPrompt(conversationState)
        val completionResponse =
            completionService.completeRequest(CompletionRequest(aiPrompt, "text-curie-001", 20, 0.0f, 1, listOf("\n"), conversationState.conversationSpecification.userName))
        return ConversationMessage(
            conversationState.conversationSpecification.chatBootName,
            completionResponse.choices[0].text.trim()
        )
    }

    private fun formatAIPrompt(conversationState: ConversationState): String = conversationState.formatToAiPrompt(
        Locale.getDefault(),
        "\n"
    )

}