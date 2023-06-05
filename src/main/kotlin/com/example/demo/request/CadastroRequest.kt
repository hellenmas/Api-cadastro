package com.example.demo.request

import com.fasterxml.jackson.annotation.JsonProperty
data class CadastroRequest(
    @JsonProperty
    val title: String,
    @JsonProperty
    val content: String,
    @JsonProperty
    val userId: String
)
