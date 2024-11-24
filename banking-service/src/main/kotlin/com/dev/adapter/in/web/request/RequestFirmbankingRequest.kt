package com.dev.adapter.`in`.web.request

import java.math.BigDecimal

data class RequestFirmbankingRequest (
    // a -> b 실무계좌로 요청을 하기 위한 Request
    val fromBankName: String,
    val fromBankAccountNumber: String,
    val toBankName: String,
    val toBankAccountNumber: String,
    val moneyAmount: BigDecimal,
)