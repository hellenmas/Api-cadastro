package com.example.demo.exception

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.LocalDate
import java.util.stream.Collectors
class GenericException : ResponseEntityExceptionHandler() {

    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {
        val body = HashMap<String, Any>().apply {
            put("timestamp", LocalDate.now())
            put("status", status.value())
            put("errors", ex.bindingResult.fieldErrors.stream().map { x -> x.defaultMessage }.collect(Collectors.toList()))
        }

        return ResponseEntity(body, headers, status)
    }
}
