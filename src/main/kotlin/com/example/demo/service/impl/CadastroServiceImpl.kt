package com.example.demo.service.impl

import com.example.demo.domain.Cadastro
import com.example.demo.response.CadastroResponse
import com.example.demo.request.CadastroRequest
import com.example.demo.repositories.CadastroRepository
import com.example.demo.service.CadastroService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class CadastroServiceImpl @Autowired constructor(
    private val cadastroRepository: CadastroRepository
) : CadastroService{
    override fun create(request: CadastroRequest): CadastroResponse {
        val user = cadastroRepository.save(
            Cadastro(
                title = request.title,
                content = request.content,
                userId = request.userId
            )
        )
        return CadastroResponse.from(user)
    }
    override fun getAll(): List<Cadastro> = cadastroRepository.findAll().toList()

    override fun getById(id: String): Optional<Cadastro> = cadastroRepository.findById(id)
}