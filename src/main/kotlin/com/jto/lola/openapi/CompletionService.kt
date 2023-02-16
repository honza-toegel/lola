package com.jto.lola.openapi

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.lang.Exception

@Service
class CompletionService (
    @Value("\${openapi.apiKey}") private val apiKey: String,
    @Value("\${openapi.orgId}") private val orgId: String
)
{
    private val restTemplate = RestTemplate()
    private val headers = HttpHeaders().apply {
        contentType = MediaType.APPLICATION_JSON
        set("Authorization", "Bearer $apiKey")
        set("OpenAI-Organization", orgId)
    }

    /**
     * Calling OpenAI completion api
     * makes sure that there is at least one answer, or throws exception
     */
    fun completeRequest(completionRequest: CompletionRequest): CompletionResponse {
        try {
            val request = HttpEntity(completionRequest, headers)
            val response = restTemplate.postForObject(
                "https://api.openai.com/v1/completions",
                request,
                CompletionResponse::class.java
            )

            if (response != null && !response.choices.isNullOrEmpty() && !response.choices[0].text.isNullOrBlank())
                return response
            else
                throw ConversationException("The openapi generated response is null or blank")

        } catch (ex : Exception) {
            throw ConversationException("Unexpected exception occurred while completing prompt by OpenAI", ex)
        }
    }
}