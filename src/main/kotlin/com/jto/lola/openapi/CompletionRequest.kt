package com.jto.lola.openapi

import com.fasterxml.jackson.annotation.JsonProperty

/*
val completionRequest = CompletionRequest(
    prompt = "What is the capital of",
    model = "text-davinci-002",
    maxTokens = 20,
    n = 1,
    stop = ""
)*/

data class CompletionRequest(
    val prompt: String,
    val model: String,
    @JsonProperty("max_tokens")
    val maxTokens: Int,
    /*
        What sampling temperature to use, between 0 and 2. Higher values like 0.8 will make the output more random, while lower values like 0.2 will make it more focused and deterministic.
        We generally recommend altering this or top_p but not both.
     */
    val temperature: Float,
    /**
     *  How many completions to generate for each prompt.
        Note: Because this parameter generates many completions, it can quickly consume your token quota. Use carefully and ensure that you have reasonable settings for max_tokens and stop.
     */
    val n: Int,
    val stop: List<String>,
    val user: String
)