package com.example.demo.service.impl

import com.example.demo.config.KafkaConfig
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.clients.producer.ProducerRecord
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class SendToKafka(val kafkaConfig: KafkaConfig) {
    @Autowired
    private lateinit var kafkaTemplate: KafkaTemplate<String, String>

    private val producerJson = kafkaConfig.producerJson()

    private val logger: Logger = LoggerFactory.getLogger(SendToKafka::class.java)
    fun sendToKafkaJson(topic: String, obj: Any) {
        val node = ObjectMapper().valueToTree<JsonNode>(obj)
        val record: ProducerRecord<String, JsonNode> = ProducerRecord(topic, node)

        logger.info("Sending message to Kafka topic: $topic")
        producerJson.send(record) { metadata, exception ->
            if (exception != null) {
                logger.error("Error sending message to Kafka topic: $topic", exception)
            } else {
                logger.info("Message sent to Kafka topic: $topic")
                logger.debug("Metadata: $metadata")
            }
        }
    }
}
