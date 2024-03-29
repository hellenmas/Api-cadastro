package com.example.demo.domain

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexRangeKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted
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
    @field:DynamoDBIndexHashKey(globalSecondaryIndexName = "post_user_id_created")
    var userId: String = "",

    @field:DynamoDBAttribute(attributeName = "title")
    @field:DynamoDBIndexHashKey(globalSecondaryIndexName = "post_title_created")
    var title: String = "",

    @field:DynamoDBAttribute(attributeName = "content")
    var content: String = "",

    @field:DynamoDBAttribute(attributeName = "created_at")
    @field:DynamoDBTypeConverted(converter = DynamoDBConfig.Companion.LocalDateTimeConverter::class)
    @field:DynamoDBIndexRangeKey(globalSecondaryIndexNames = ["post_user_id_created", "post_title_created"])
    var createdAt: LocalDateTime = now()
)
