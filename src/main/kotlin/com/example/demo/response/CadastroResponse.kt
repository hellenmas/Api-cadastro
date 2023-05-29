package com.example.demo.response

import com.example.demo.domain.Cadastro
import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class CadastroResponse(
    val id: String,
    val title: String,
    val content: String,
    val userId: String,
    @field:JsonFormat(pattern = "yyyy-MM-dd")
    val createdAt: LocalDateTime
) {
    companion object {
        fun from(cadastro: Cadastro) = CadastroResponse(
            id = cadastro.id,
            title = cadastro.title,
            content = cadastro.content,
            userId = cadastro.userId,
            createdAt = cadastro.createdAt
        )
    }
}