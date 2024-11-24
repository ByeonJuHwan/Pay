package com.dev.domain

import com.dev.domain.type.MoneyChangingStatus
import com.dev.domain.type.MoneyChangingType
import java.math.BigDecimal
import java.util.Date
import java.util.UUID

class MoneyChangingRequest private constructor(
    val moneyChangingRequestId: String,
    // 어떤 고객의 증액/감액 요청을 요청했는지의 멤버 정보
    val targetMembershipId: String,
    // 그 요청이 증액 요청인지 / 감액 요청인지
    val changingType: MoneyChangingType,
    val bankAccountNumber: String,
    val changingMoneyAmount: BigDecimal,
    val changingMoneyStatus: MoneyChangingStatus,
    val uuid: UUID,
    val createdAt: Date,
) {
    data class MoneyChangingRequestId(
        val moneyChangingRequestId: String
    )

    data class TargetMembershipId(
        val targetMembershipId: String
    )

    data class ChangingType(
        val changingType: String
    )

    data class BankAccountNumber(
        val bankAccountNumber: String
    )

    data class ChangingMoneyAmount(
        val changingMoneyAmount: BigDecimal,
    )

    data class ChangingMoneyStatus(
        val changingMoneyStatus: String
    )

    data class Uuid(
        val uuid: UUID
    )

    companion object {
        fun generateMoneyChangingRequest(
            moneyChangingRequestId: MoneyChangingRequestId,
            targetMembershipId: TargetMembershipId,
            changingType: MoneyChangingType,
            bankAccountNumber: BankAccountNumber,
            changingMoneyAmount: ChangingMoneyAmount,
            changingMoneyStatus: MoneyChangingStatus,
            uuid: Uuid,
        ): MoneyChangingRequest {
            return MoneyChangingRequest(
                moneyChangingRequestId = moneyChangingRequestId.moneyChangingRequestId,
                targetMembershipId = targetMembershipId.targetMembershipId,
                changingType = changingType,
                bankAccountNumber = bankAccountNumber.bankAccountNumber,
                changingMoneyAmount = changingMoneyAmount.changingMoneyAmount,
                changingMoneyStatus = changingMoneyStatus,
                uuid = uuid.uuid,
                createdAt = Date(),
            )
        }
    }
}