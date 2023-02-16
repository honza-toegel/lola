package com.jto.lola.openapi

import com.fasterxml.jackson.annotation.JsonProperty

data class CompletionResponse(
    val id: String,
    @JsonProperty("object")
    val objectType: String,
    val model: String,
    val choices: List<Choice>,
    val usage: Usage,
)

data class Choice(
    val text: String,
    val index: Int,
    @JsonProperty("logprobs")
    val logProbs: Int?,
    @JsonProperty("finish_reason")
    val finishReason: String,
)

data class Usage(
    @JsonProperty("prompt_tokens")
    val promptTokens: Int,
    @JsonProperty("completion_tokens")
    val completionTokens: Int,
    @JsonProperty("total_tokens")
    val totalTokens: Int,
)

/*
{
    "id": "cmpl-GERzeJQ4lvqPk8SkZu4XMIuR",
    "object": "text_completion",
    "created": 1586839808,
    "model": "text-davinci:003",
    "choices": [
        {
            "text": "\n\nThis is indeed a test",
            "index": 0,
            "logprobs": null,
            "finish_reason": "length"
        }
    ],
    "usage": {
        "prompt_tokens": 5,
        "completion_tokens": 7,
        "total_tokens": 12
    }
}
 */