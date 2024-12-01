package com.dev

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TaskConsumerApplication

fun main(args: Array<String>) {
    runApplication<TaskConsumerApplication>(*args)
}