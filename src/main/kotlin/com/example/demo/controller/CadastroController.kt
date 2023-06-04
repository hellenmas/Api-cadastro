package com.example.demo.controller

import com.example.demo.domain.Cadastro
import com.example.demo.event.SendToKafka
import com.example.demo.request.CadastroRequest
import com.example.demo.service.CadastroService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
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

    private val logger: Logger = LoggerFactory.getLogger(CadastroController::class.java)

    @GetMapping
    fun getAll(): List<Cadastro> {
        logger.info("Getting all Cadastro entries")
        return cadastroService.getAll()
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable(value = "id") id: String): Optional<Cadastro> {
        logger.info("Getting Cadastro by id: $id")
        return cadastroService.getById(id)
    }

    @PostMapping
    fun singup(@RequestBody request: CadastroRequest): ResponseEntity<Any> {
        logger.info("Creating Cadastro entry")
        return try {
            cadastroService.create(request)
            sendToKafka.sendToKafkaJson(topic, request)
            logger.info("Cadastro entry created and sent to Kafka successfully")
            ResponseEntity.ok().build()
        } catch (e: Exception) {
            logger.error("Error occurred while creating Cadastro entry: ${e.message}")
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Problema ao enviar mensagem")
        }
    }

    @PutMapping
    fun update(@RequestBody request: CadastroRequest) {
        logger.info("Updating Cadastro entry")
        cadastroService.update(request)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable(value = "id") id: String) {
        logger.info("Deleting Cadastro entry by id: $id")
        cadastroService.delete(id)
    }
}
