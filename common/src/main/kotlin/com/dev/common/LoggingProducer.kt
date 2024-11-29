package com.dev.common

import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*

@Component
class LoggingProducer (
    @Value("\${kafka.clusters.bootstrapservers}") private val bootstrapServers: String,
    @Value("\${logging.topic}") private val topic: String,
) {
    private val producer: KafkaProducer<String, String> = KafkaProducer(Properties().apply {
        put("bootstrap.servers", bootstrapServers)
        put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
        put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    })


    fun sendMessage(key: String, value: String) {
        val record = ProducerRecord(topic, key, value)
        producer.send(record) { metadata, exception ->
            when (exception) {
                null -> {
                     println("Message sent successfully. Offset: ${metadata.offset()}")
                }
                else -> {
                    exception.printStackTrace()
                     println("Failed to send message: ${exception.message}")
                }
            }

        }
    }
}
