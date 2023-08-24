package com.jto.lola.openapi

import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Service

@Primary
@Service
class ChatBootServiceMockImpl : ChatBootService {
    override fun getChatBootReaction(conversationState: ConversationState): ConversationMessage {
        Thread.sleep(2000)
        return ConversationMessage(conversationState.conversationSpecification.chatBootName,"Mocked response: blablabla...")
    }
}