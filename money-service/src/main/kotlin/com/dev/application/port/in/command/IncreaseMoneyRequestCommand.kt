package com.dev.application.port.`in`.command

import java.math.BigDecimal

data class IncreaseMoneyRequestCommand (
    val targetMembershipId: String,
    val amount: BigDecimal,
)