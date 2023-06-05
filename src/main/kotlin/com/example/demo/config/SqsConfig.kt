package com.example.demo.config

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.sqs.AmazonSQSAsync
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder
import io.awspring.cloud.messaging.config.QueueMessageHandlerFactory
import io.awspring.cloud.messaging.support.NotificationMessageArgumentResolver
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.messaging.converter.MappingJackson2MessageConverter
import org.springframework.messaging.converter.MessageConverter
import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver
import java.util.List

@Configuration
class SqsConfig {
    @Value("\${app.config.accessKey}")
    private lateinit var access: String

    @Value("\${app.config.secretKey}")
    private lateinit var secret: String

    @Value("\${app.config.region}")
    private lateinit var region: String

    @Value("\${app.config.endpoint}")
    private lateinit var endpoint: String

    @Bean
    fun endpointConfiguration(): AwsClientBuilder.EndpointConfiguration? {
        return AwsClientBuilder.EndpointConfiguration(endpoint, region)
    }

    @Bean
    @Primary
    fun amazonSQSAsync(endpointConfiguration: AwsClientBuilder.EndpointConfiguration?): AmazonSQSAsync? {
        val credentials = BasicAWSCredentials(access, secret)
        return AmazonSQSAsyncClientBuilder
            .standard()
            .withEndpointConfiguration(endpointConfiguration)
            .withCredentials(AWSStaticCredentialsProvider(credentials))
            .build()
    }

    @Bean
    fun queueMessageHandlerFactory(messageConverter: MessageConverter?): QueueMessageHandlerFactory? {
        val factory = QueueMessageHandlerFactory()
        factory.setArgumentResolvers(
            List.of<HandlerMethodArgumentResolver>(
                NotificationMessageArgumentResolver(
                    messageConverter
                )
            )
        )
        return factory
    }

    @Bean
    fun messageConverter(): MessageConverter {
        val converter = MappingJackson2MessageConverter()
        converter.setSerializedPayloadClass(String::class.java)
        converter.isStrictContentTypeMatch = false
        return converter
    }
}
