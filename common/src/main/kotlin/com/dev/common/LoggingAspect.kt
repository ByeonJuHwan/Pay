package com.dev.common

import jakarta.validation.constraints.NotNull
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.springframework.stereotype.Component


@Aspect
@Component
class LoggingAspect (
    private val loggingProducer: LoggingProducer,
) {

    @Before("within(com.dev..*.web.*Controller)")
    fun beforeMethodExecution(@NotNull joinPoint: JoinPoint) {
        val methodName = joinPoint.signature.name
        loggingProducer.sendMessage("logging", "Before Executing method: $methodName")
    }
}