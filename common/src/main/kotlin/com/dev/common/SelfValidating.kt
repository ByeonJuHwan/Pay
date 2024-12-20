package com.dev.common

import jakarta.validation.*

abstract class SelfValidating<T> {
    private val validator: Validator

    init {
        val factory : ValidatorFactory = Validation.buildDefaultValidatorFactory()
        validator = factory.validator
    }

    @Suppress("UNCHECKED_CAST")
    protected fun validateSelf() {
        val violations: Set<ConstraintViolation<T>> = validator.validate(this as T)
        if (violations.isNotEmpty()) {
            throw ConstraintViolationException(violations)
        }
    }
}