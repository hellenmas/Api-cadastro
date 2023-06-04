package com.example.demo

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HellenEstudoApplication

private val logger: Logger = LoggerFactory.getLogger(HellenEstudoApplication::class.java)
fun main(args: Array<String>) {
    logger.info("iniciando a api de cadastro")
    runApplication<HellenEstudoApplication>(*args)
    logger.info("api de cadastro iniciada e pronta para receber requisicoes")
}
