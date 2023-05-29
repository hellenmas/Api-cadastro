package com.example.demo.domain

import com.amazonaws.services.dynamodbv2.datamodeling.*
import com.example.demo.config.DynamoDBConfig
import java.time.LocalDateTime
import java.time.LocalDateTime.now
import java.util.*

@DynamoDBTable(tableName = "posts")
class Cadastro(
    @field:DynamoDBHashKey
    @field:DynamoDBAttribute(attributeName = "post_id")
    var id: String = UUID.randomUUID().toString(),

    @field:DynamoDBAttribute(attributeName = "user_id")
    @field:DynamoDBIndexHashKey(globalSecondaryIndexName = "post_user_id_created_at_idx")
    var userId: String = "",

    @field:DynamoDBAttribute(attributeName = "title")
    @field:DynamoDBIndexHashKey(globalSecondaryIndexName = "post_title_created_at_idx")
    var title: String = "",

    @field:DynamoDBAttribute(attributeName = "content")
    var content: String = "",

    @field:DynamoDBAttribute(attributeName = "created_at")
    @field:DynamoDBTypeConverted(converter = DynamoDBConfig.Companion.LocalDateTimeConverter::class)
    @field:DynamoDBIndexRangeKey(globalSecondaryIndexNames = ["post_user_id_created_at_idx", "post_title_created_at_idx"])
    var createdAt: LocalDateTime = now()
)