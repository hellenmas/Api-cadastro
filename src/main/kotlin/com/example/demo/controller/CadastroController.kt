package com.example.demo.controller

import com.example.demo.request.CadastroRequest
import com.example.demo.service.CadastroService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("cadastro")
class CadastroController (
        private val cadastroService: CadastroService
) {

    @PostMapping
    fun singup(@RequestBody request: CadastroRequest) = cadastroService.create(request)

}