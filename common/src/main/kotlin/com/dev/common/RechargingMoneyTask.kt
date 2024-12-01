package com.dev.common

import java.math.BigDecimal

data class RechargingMoneyTask(
    val taskId: String,
    val taskName: String,
    val membershipId: String,
    val subTaskList: List<SubTask>,
    val toBankName: String, // 법인 계좌
    val toBankAccountNumber: String, // 법인 계좌 번호
    val moneyAmount: BigDecimal,
)
