package com.dev.application.port.out

import com.dev.adapter.out.persistence.MemberMoneyJpaEntity
import com.dev.adapter.out.persistence.MoneyChangingRequestJpaEntity
import com.dev.domain.MemberMoney
import com.dev.domain.MoneyChangingRequest
import java.math.BigDecimal

interface IncreaseMoneyPort {
    fun createMoneyChangingRequest(
        targetMembershipId: MoneyChangingRequest.TargetMembershipId,
        moneyChangingType: MoneyChangingRequest.ChangingType,
        changingMoneyAmount: MoneyChangingRequest.ChangingMoneyAmount,
        moneyChangingMoneyStatus: MoneyChangingRequest.ChangingMoneyStatus,
        uuid: MoneyChangingRequest.Uuid,
    ) : MoneyChangingRequestJpaEntity

    fun increaseMoney(
        membershipId: MemberMoney.MembershipId,
        increaseAmount: BigDecimal,
    ) : MemberMoneyJpaEntity
}