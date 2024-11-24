package com.dev.adapter.`in`.response

import com.dev.domain.MoneyChangingRequest
import com.dev.domain.type.MoneyChangingStatus
import com.dev.domain.type.MoneyChangingType
import java.math.BigDecimal

data class MoneyChangingResultDetail (
    val moneyChangingRequestId: String,
    val amount: BigDecimal,
    val moneyChangingType: MoneyChangingType,
    val moneyChangingResultStatus: MoneyChangingStatus,
) {
    companion object {
        fun from (moneyChangingRequest: MoneyChangingRequest): MoneyChangingResultDetail {
            return MoneyChangingResultDetail(
                moneyChangingRequestId = moneyChangingRequest.moneyChangingRequestId,
                amount = moneyChangingRequest.changingMoneyAmount,
                moneyChangingType = moneyChangingRequest.changingType,
                moneyChangingResultStatus = moneyChangingRequest.changingMoneyStatus
            )
        }
    }
}