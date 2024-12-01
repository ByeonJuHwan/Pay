package com.dev.adapter.out.kafka

import com.dev.application.port.out.SendRechargingMoneyTaskPort
import com.dev.common.RechargingMoneyTask
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*

@Component
class TaskProducer (
    @Value("\${kafka.clusters.bootstrapservers}") private val bootstrapServers: String,
    @Value("\${task.topic}") private val topic: String,
    private val mapper: ObjectMapper,
) : SendRechargingMoneyTaskPort{

    private val producer: KafkaProducer<String, String> = KafkaProducer(Properties().apply {
        put("bootstrap.servers", bootstrapServers)
        put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
        put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    })

    override fun sendRechargingMoneyTaskPort(task: RechargingMoneyTask) {
        this.sendMessage(task.taskId, task)
    }

    fun sendMessage(key: String, value: RechargingMoneyTask) {

        val jsonStringToProduce = mapper.writeValueAsString(value)

        val record = ProducerRecord(topic, key, jsonStringToProduce)
        producer.send(record) { metadata, exception ->
            when (exception) {
                null -> println("Message sent successfully. Offset: ${metadata.offset()}")
                else -> {
                    exception.printStackTrace(System.err)
                    println("Failed to send message: ${exception.message}")
                }
            }
        }
    }
}