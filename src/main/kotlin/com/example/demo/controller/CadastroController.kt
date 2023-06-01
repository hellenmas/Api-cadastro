package com.example.demo.controller

import com.example.demo.domain.Cadastro
import com.example.demo.request.CadastroRequest
import com.example.demo.service.CadastroService
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.*
import java.util.Optional

@RestController
@RequestMapping("cadastro")
class CadastroController (
        private val cadastroService: CadastroService
) {
    @GetMapping
    fun getAll(): List<Cadastro> = cadastroService.getAll()

    @GetMapping("/{id}")
    @ApiOperation(value = "Get by Id", response = Cadastro::class)
    fun getById(@PathVariable(value = "id") id: String): Optional<Cadastro> = cadastroService.getById(id)

    @PostMapping
    fun singup(@RequestBody request: CadastroRequest) = cadastroService.create(request)

}