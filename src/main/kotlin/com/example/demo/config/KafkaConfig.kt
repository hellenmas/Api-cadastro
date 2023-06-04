package com.example.demo.config
import com.example.demo.service.impl.CadastroServiceImpl
import com.fasterxml.jackson.databind.JsonNode
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.support.serializer.JsonSerializer
@Configuration
class KafkaConfig {
    @Value("\${bootstrap.servers}")
    private lateinit var servers : String

    private val logger: Logger = LoggerFactory.getLogger(KafkaConfig::class.java)

    private fun config() : Map<String, Any> {
        return HashMap<String, Any>().apply {
            put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, servers)
            put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer::class.java)
            put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer::class.java)

        }
    }
    @Bean
    fun producerJson() : KafkaProducer<String, JsonNode> {
        logger.info("Creating Kafka Producer for JSON")
        val config = config()
        val producer = KafkaProducer<String, JsonNode>(config)
        logger.info("Kafka Producer for JSON created")
        return producer
    }
}