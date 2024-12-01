package com.dev.adapter.`in`.kafka

import com.dev.common.CountDownLatchManager
import com.dev.common.KafkaStatus
import com.dev.common.LoggingProducer
import com.dev.common.RechargingMoneyTask
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.time.Duration
import java.util.*

@Component
class RechargingMoneyResultConsumer (
    @Value("\${kafka.clusters.bootstrapservers}") private val bootstrapServers: String,
    @Value("\${task.result.topic}") private val topic: String,
    private val loggingProducer: LoggingProducer,
    private val countDownLatchManager: CountDownLatchManager,
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
                        println("Received message : ${record.key()} / ${record.value()}")

                        // task run
                        val task = mapper.readValue(record.value().trim('"'), RechargingMoneyTask::class.java)

                        // task result
                        val taskResult = !task.subTaskList.any {it.status == "fail"}

                        if (taskResult) {
                            this.loggingProducer.sendMessage(task.taskId, "task success")
                            this.countDownLatchManager.setDataForKey(task.taskId, KafkaStatus.SUSSES.status)
                        } else {
                            this.loggingProducer.sendMessage(task.taskId, "task failed")
                            this.countDownLatchManager.setDataForKey(task.taskId,KafkaStatus.FAILED.status)
                        }

                        this.countDownLatchManager.getCountDownLatch("rechargingMoneyTask")?.countDown()

                    }
                }
            }
        }.start()
    }
}