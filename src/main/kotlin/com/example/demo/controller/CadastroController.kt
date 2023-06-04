package com.example.demo.controller

import com.example.demo.domain.Cadastro
import com.example.demo.request.CadastroRequest
import com.example.demo.service.CadastroService
import com.example.demo.service.impl.SendToKafka
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.Optional

@RestController
@RequestMapping("cadastro")
class CadastroController(
    private val cadastroService: CadastroService
) {
    @Autowired
    private lateinit var sendToKafka: SendToKafka

    @Value("\${topic-person}")
    lateinit var topic: String

    @GetMapping
    fun getAll(): List<Cadastro> = cadastroService.getAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable(value = "id") id: String): Optional<Cadastro> = cadastroService.getById(id)

    @PostMapping
    fun singup(@RequestBody request: CadastroRequest): ResponseEntity<Any> {
        return try {
            cadastroService.create(request)
            sendToKafka.sendToKafkaJson(topic, request)
            ResponseEntity.ok().build()
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Problema ao enviar mensagem")
        }
    }

    @PutMapping
    fun update(@RequestBody request: CadastroRequest) = cadastroService.update(request)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable(value = "id") id: String) = cadastroService.delete(id)
}
