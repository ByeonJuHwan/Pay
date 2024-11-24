package com.dev.adapter.`in`.request

import java.math.BigDecimal

data class IncreaseMoneyChangingRequest(
    val targetMembershipId: String,
    val amount: BigDecimal,
)