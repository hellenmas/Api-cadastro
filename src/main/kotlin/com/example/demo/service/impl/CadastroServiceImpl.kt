package com.example.demo.service.impl

import com.example.demo.domain.Cadastro
import com.example.demo.repositories.CadastroRepository
import com.example.demo.request.CadastroRequest
import com.example.demo.response.CadastroResponse
import com.example.demo.service.CadastroService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
@Service
class CadastroServiceImpl @Autowired constructor(
    private val cadastroRepository: CadastroRepository
) : CadastroService {

    private val logger: Logger = LoggerFactory.getLogger(CadastroServiceImpl::class.java)
    override fun create(request: CadastroRequest): CadastroResponse {
        logger.info("Creating Cadastro")
        val user = cadastroRepository.save(
            Cadastro(
                title = request.title,
                content = request.content,
                userId = request.userId
            )
        )
        logger.info("Cadastro created: $user")
        return CadastroResponse.from(user)
    }
    override fun getAll(): List<Cadastro> {
        logger.info("Getting all Cadastros")
        val cadastros = cadastroRepository.findAll().toList()
        logger.info("Retrieved ${cadastros.size} Cadastros")
        return cadastros
    }
    override fun getById(id: String): Optional<Cadastro> {
        logger.info("Getting Cadastro by id: $id")
        val cadastro = cadastroRepository.findById(id)
        if (cadastro.isPresent) {
            logger.info("Retrieved Cadastro: ${cadastro.get()}")
        } else {
            logger.info("Cadastro not found with id: $id")
        }
        return cadastro
    }
    override fun update(request: CadastroRequest): CadastroResponse {
        logger.info("Updating Cadastro")
        val user = cadastroRepository.save(
            Cadastro(
                title = request.title,
                content = request.content,
                userId = request.userId
            )
        )
        logger.info("Cadastro updated: $user")
        return CadastroResponse.from(user)
    }
    override fun delete(id: String): String {
        logger.info("Deleting Cadastro with id: $id")
        if (!cadastroRepository.existsById(id)) {
            logger.info("Cadastro not found with id: $id")
            return "Cadastro não existe na base de dados"
        }
        cadastroRepository.deleteById(id)
        logger.info("Cadastro deleted with id: $id")
        return "Cadastro removido com sucesso!"
    }
}
