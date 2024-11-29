package com.dev

import org.apache.kafka.clients.consumer.KafkaConsumer
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.time.Duration
import java.util.*

@Component
class LoggingConsumer (
    @Value("\${kafka.clusters.bootstrapservers}") private val bootstrapServers: String,
    @Value("\${logging.topic}") private val topic: String,
) {

    private lateinit var consumer: KafkaConsumer<String, String>

    init {
        val props = Properties().apply {
            put("bootstrap.servers", bootstrapServers)
            put("group.id", "my-group")
            put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
            put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
        }

        consumer = KafkaConsumer<String, String>(props).apply {
            subscribe(listOf(topic))
        }

        Thread {
            consumer.use { consumer ->
                while (true) {
                    consumer.poll(Duration.ofSeconds(1)).forEach { record ->
                        println("Received: ${record.value()}")
                    }
                }
            }
        }.start()
    }
}