package com.dev.domain

import java.math.BigDecimal
import java.util.UUID

class FirmBankingRequest private constructor(
    val firmBankingRequestId: String,
    val fromBankName: String,
    val fromBankAccountNumber: String,
    val toBankName: String,
    val toBankAccountNumber: String,
    val moneyAmount: BigDecimal,
    val firmBankingStatus: Int, // 0 : 요청, 1 : 완료, 2: 실패
    val uuid: UUID?,
) {
    data class FirmBankingRequestId(
        val firmBankingRequestId: String
    )

    data class FromBankName(
        val fromBankName: String
    )

    data class FromBankAccountNumber(
        val fromBankAccountNumber: String
    )

    data class ToBankName(
        val toBankName: String
    )

    data class ToBankAccountNumber(
        val toBankAccountNumber: String
    )

    data class MoneyAmount(
        val moneyAmount: BigDecimal,
    )

    data class FirmBankingStatus(
        val firmBankingStatus: Int
    )

    companion object {
        fun generateFirmBankingRequest(
            firmBankingRequestId: FirmBankingRequestId,
            fromBankName: FromBankName,
            fromBankAccountNumber: FromBankAccountNumber,
            toBankName: ToBankName,
            toBankAccountNumber: ToBankAccountNumber,
            moneyAmount: MoneyAmount,
            firmBankingStatus: FirmBankingStatus,
            uuid: UUID?,
        ): FirmBankingRequest {
            return FirmBankingRequest(
                firmBankingRequestId = firmBankingRequestId.firmBankingRequestId,
                fromBankName = fromBankName.fromBankName,
                fromBankAccountNumber = fromBankAccountNumber.fromBankAccountNumber,
                toBankName = toBankName.toBankName,
                toBankAccountNumber = toBankAccountNumber.toBankAccountNumber,
                moneyAmount = moneyAmount.moneyAmount,
                firmBankingStatus = firmBankingStatus.firmBankingStatus,
                uuid = uuid,
            )
        }
    }
}