package com.example.demo.config

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

@Configuration
@EnableDynamoDBRepositories(basePackages = ["com.example.demo.repositories"])
class DynamoDBConfig(
    @Value("\${amazon.dynamodb.endpoint}") private val endpoint: String,
    @Value("\${amazon.aws.accessKey}") private val accessKey: String,
    @Value("\${amazon.aws.secretKey}") private val secretKey: String,
    @Value("\${amazon.aws.region}") private val region: String
) {

    private val logger: Logger = LoggerFactory.getLogger(DynamoDBConfig::class.java)
    companion object {
        class LocalDateTimeConverter : DynamoDBTypeConverter<Date, LocalDateTime> {
            override fun convert(source: LocalDateTime): Date {
                return Date.from(source.toInstant(ZoneOffset.UTC))
            }

            override fun unconvert(source: Date): LocalDateTime {
                return source.toInstant().atZone(TimeZone.getDefault().toZoneId()).toLocalDateTime()
            }
        }
    }

    @Primary
    @Bean
    fun dynamoDBMapper(amazonDynamoDB: AmazonDynamoDB): DynamoDBMapper {
        logger.info("Creating DynamoDBMapper")
        val mapper = DynamoDBMapper(amazonDynamoDB, DynamoDBMapperConfig.DEFAULT)
        logger.info("DynamoDBMapper created")
        return mapper
    }

    @Bean
    fun amazonDynamoDB(): AmazonDynamoDB {
        logger.info("Creating AmazonDynamoDB client")
        val awsCredentials = BasicAWSCredentials(accessKey, secretKey)
        val awsCredentialsProvider = AWSStaticCredentialsProvider(awsCredentials)
        val endpointConfiguration = AwsClientBuilder.EndpointConfiguration(endpoint, region)
        val client = AmazonDynamoDBClientBuilder.standard()
            .withCredentials(awsCredentialsProvider)
            .withEndpointConfiguration(endpointConfiguration)
            .build()
        logger.info("AmazonDynamoDB client created")
        return client
    }

    @Bean
    fun awsCredentials() = BasicAWSCredentials(accessKey, secretKey)
}
