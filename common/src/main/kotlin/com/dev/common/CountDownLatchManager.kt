package com.dev.common

import org.springframework.context.annotation.Configuration
import java.util.concurrent.CountDownLatch

@Configuration
class CountDownLatchManager (
    private val countDownLatchMap: MutableMap<String, CountDownLatch> = mutableMapOf(),
    private val stringMap: MutableMap<String, String> = mutableMapOf()
) {

    fun addCountDownLatch(key: String) {
        this.countDownLatchMap[key] = CountDownLatch(1)
    }

    fun setDataForKey(key: String, data: String) {
        this.stringMap[key] = data
    }

    fun getDataForKey(key: String): String? {
        return this.stringMap[key]
    }

    fun getCountDownLatch(key: String): CountDownLatch? {
        return this.countDownLatchMap[key]
    }
}