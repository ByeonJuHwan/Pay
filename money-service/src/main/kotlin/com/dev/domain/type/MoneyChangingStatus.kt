package com.dev.domain.type

enum class MoneyChangingStatus {
    SUCCEEDED,
    FAILED,
    REQUESTED,
    CANCELLED,
    FAILED_NOT_ENOUGH_MONEY,
    FAILED_NOT_EXIST_MEMBERSHIP,
}