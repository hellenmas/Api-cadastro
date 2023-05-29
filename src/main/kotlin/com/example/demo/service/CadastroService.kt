package com.example.demo.service
import com.example.demo.response.CadastroResponse
import com.example.demo.request.CadastroRequest

interface CadastroService {

    fun create(request: CadastroRequest): CadastroResponse

}