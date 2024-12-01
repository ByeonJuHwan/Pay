package com.dev

import com.dev.common.KafkaStatus
import com.dev.common.RechargingMoneyTask
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.time.Duration
import java.util.*

@Component
class TaskConsumer (
    @Value("\${kafka.clusters.bootstrapservers}") private val bootstrapServers: String,
    @Value("\${task.topic}") private val topic: String,
    private val taskResultProducer: TaskResultProducer,
    private val mapper: ObjectMapper,
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

                        // task run
                        val task = mapper.readValue(record.value(), RechargingMoneyTask::class.java)

                        // task result

                        task.subTaskList.forEach { subTask ->

                            // what subTask, membership, banking
                            // 모든 서브젝트가 잘 되었다는 가정 Is true
                            subTask.status = KafkaStatus.SUSSES.status

                        }

                        // produce TaskResult
                        taskResultProducer.sendMessage(record.key(), task)

                    }
                }
            }
        }.start()
    }
}