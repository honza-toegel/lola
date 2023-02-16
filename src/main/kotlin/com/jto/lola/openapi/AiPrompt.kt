package com.jto.lola.openapi

import java.util.*

interface AiPrompt {
    /**
     * Translate the meaning of the object in the human text
     * so that it's understandable for AI prompt APIs
     */
    fun formatToAiPrompt(locale: Locale, stopSequence: String): String
}