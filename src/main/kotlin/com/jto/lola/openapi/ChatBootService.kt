package com.jto.lola.openapi

interface ChatBootService {
    fun getChatBootReaction(
        conversationState: ConversationState
    ): ConversationMessage
}