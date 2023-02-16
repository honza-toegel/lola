package com.jto.lola.messages

import com.jto.lola.SpringUtils
import com.jto.lola.openapi.ChatBootService
import com.jto.lola.openapi.ConversationMessage
import com.jto.lola.openapi.ConversationSpecification
import com.jto.lola.openapi.ConversationState
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.web.bind.annotation.*
import java.time.ZonedDateTime
import java.util.*

@EnableScheduling
@RestController
@RequestMapping("/conversations/{conversationId}/messages")
@CrossOrigin(origins = ["http://localhost:8081"])
class ChatMessagesRestController(private val chatBootService: ChatBootService) {

    private val chatBootUserId = "LoLaBoot"
    private val userId = "user1"

    val chatMessages = mutableListOf(
        ChatMessage(UUID.randomUUID(), chatBootUserId, "Hi! how are you?", "Ola, como esta?", true, ZonedDateTime.now().minusMinutes(1)),
        //ChatMessage(2, userId, "Hi! hows life?", "Ola como esta?", false, ZonedDateTime.now().minusMinutes(1)),
        //ChatMessage(3, chatBootUserId, "Yeap, was nice day today, or?", "Mui buen?", true, ZonedDateTime.now().minusMinutes(1))
    )

    @PostMapping
    fun sendMessage(
        @PathVariable conversationId: Long,
        @RequestBody chatMessageRequest: ChatMessageRequest
    ): ChatMessage {
        Thread.sleep(1000)
        val newChatMessage = ChatMessage.fromChatMessageRequest(chatMessageRequest, SpringUtils.getCurrentUserId())
        chatMessages.add(newChatMessage)
        return newChatMessage
    }

    @Scheduled(cron = "*/20 * * * * *")
    fun mockChatBot() {
        if (chatMessages.isNotEmpty() && !chatMessages.last().isIncoming) {
            val conversationState = ConversationState(ConversationSpecification(
                null,
                listOf("friendly", "helpful", "cheerful", "proactive"),
                chatBootUserId,
                "user1",
                "8 year old child"
            ),
                chatMessages.map { chatMessage ->
                    ConversationMessage(
                        chatMessage.senderUserId,
                        chatMessage.originalText
                    )
                }
            )
            val chatBootMessage = chatBootService.getChatBootReaction(conversationState)

            chatMessages.add(
                ChatMessage(
                    UUID.randomUUID(),
                    chatBootUserId,
                    chatBootMessage.message,
                    "Translated: ${chatBootMessage.message}",
                    true,
                    ZonedDateTime.now()
                )
            )
        }
    }

    @GetMapping
    fun getMessages(@PathVariable conversationId: Long) = chatMessages
}