package com.example.demo.event.sqs

import com.amazonaws.services.sqs.AmazonSQS
import com.amazonaws.services.sqs.model.SendMessageRequest
import com.amazonaws.services.sqs.model.SendMessageResult
import com.example.demo.request.CadastroRequest
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*

@Service
class SQSEventPublisher(private val amazonSQS: AmazonSQS) {
    private val logger = LoggerFactory.getLogger(SQSEventPublisher::class.java)

    fun publishMessage(queueUrl: String, message: CadastroRequest): SendMessageResult? {
        val objectMapper = ObjectMapper()
        var sendMessageRequest: SendMessageRequest? = null
        try {
            sendMessageRequest = SendMessageRequest()
                .withQueueUrl(queueUrl)
                .withMessageBody(objectMapper.writeValueAsString(message))
            return amazonSQS.sendMessage(sendMessageRequest)
        } catch (e: JsonProcessingException) {
            logger.error("JsonProcessingException: {}", e.message)
        } catch (e: Exception) {
            logger.error("Exception: {}", e.message)
        }
        return null
    }
}
