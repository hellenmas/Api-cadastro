package com.example.demo.service
import com.example.demo.domain.Cadastro
import com.example.demo.request.CadastroRequest
import com.example.demo.response.CadastroResponse
import java.util.Optional

interface CadastroService {

    fun create(request: CadastroRequest): CadastroResponse
    fun getAll(): List<Cadastro>
    fun getById(id: String): Optional<Cadastro>
    fun update(request: CadastroRequest): CadastroResponse
    fun delete(id: String): String
}
