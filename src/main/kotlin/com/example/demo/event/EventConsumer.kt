package com.example.demo.event

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class EventConsumer {
    @Value("\${topic-person}")
    lateinit var topic: String

    private val logger: Logger = LoggerFactory.getLogger(EventConsumer::class.java)

    @KafkaListener(topics = ["\${topic-person}"], groupId = "group_id")
    fun consume(payload: ConsumerRecord<String?, String?>) {
        logger.info("Tópico: {}", topic)
        logger.info("key: {}", payload.key())
        logger.info("Headers: {}", payload.headers())
        logger.info("Partion: {}", payload.partition())
        logger.info("Order: {}", payload.value())
    }
}
