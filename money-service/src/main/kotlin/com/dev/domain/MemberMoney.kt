package com.dev.domain

import java.math.BigDecimal

class MemberMoney private constructor (
    val memberMoneyId: String,
    val membershipId: String,
    val balance: BigDecimal,
) {
    data class MemberMoneyId(
        val memberMoneyId: String
    )
    data class MembershipId(
        val membershipId: String
    )
    data class Balance(
        val balance: BigDecimal
    )

    companion object {
        fun generateMemberMoney (
            memberMoneyId: MemberMoney.MemberMoneyId,
            membershipId: MemberMoney.MembershipId,
            balance: MemberMoney.Balance,
        ) : MemberMoney {
            return MemberMoney(
                memberMoneyId.memberMoneyId,
                membershipId.membershipId,
                balance.balance
            )
        }
    }
}