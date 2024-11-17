package com.dev

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MembershipApplication

fun main(args: Array<String>) {
    runApplication<MembershipApplication>(*args)
}