package com.dev.common

enum class KafkaStatus (
    val status: String,
) {
    SUSSES("SUCCESS"),
    FAILED("FAILED"),
    READY("READY"),
}