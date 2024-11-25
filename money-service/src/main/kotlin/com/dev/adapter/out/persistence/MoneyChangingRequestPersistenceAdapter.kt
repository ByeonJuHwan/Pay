package com.dev.adapter.out.persistence

import com.dev.application.port.out.IncreaseMoneyPort
import com.dev.common.PersistenceAdapter
import com.dev.domain.MemberMoney
import com.dev.domain.MoneyChangingRequest
import org.springframework.data.repository.findByIdOrNull
import java.math.BigDecimal

@PersistenceAdapter
class MoneyChangingRequestPersistenceAdapter (
    private val moneyRepository: SpringDataMoneyChangingRequestRepository,
    private val memberMoneyRepository: SpringDataMemberMoneyRepository,
) : IncreaseMoneyPort {

    override fun createMoneyChangingRequest(
        targetMembershipId: MoneyChangingRequest.TargetMembershipId,
        moneyChangingType: MoneyChangingRequest.ChangingType,
        changingMoneyAmount: MoneyChangingRequest.ChangingMoneyAmount,
        moneyChangingMoneyStatus: MoneyChangingRequest.ChangingMoneyStatus,
        uuid: MoneyChangingRequest.Uuid
    ): MoneyChangingRequestJpaEntity {
        return moneyRepository.save(
            MoneyChangingRequestJpaEntity(
                targetMembershipId = targetMembershipId.targetMembershipId,
                moneyChangingType = moneyChangingType.changingType,
                moneyAmount = changingMoneyAmount.changingMoneyAmount,
                changingMoneyStatus = moneyChangingMoneyStatus.changingMoneyStatus,
                uuid = uuid.uuid.toString(),
            )
        )
    }

    override fun increaseMoney(
        membershipId: MemberMoney.MembershipId,
        increaseAmount: BigDecimal
    ) : MemberMoneyJpaEntity {

        val entity = memberMoneyRepository.findByIdOrNull(membershipId.membershipId.toLong())

        if(entity == null) {

            val newMemberMoneyEntity = MemberMoneyJpaEntity(
                membershipId.membershipId,
                increaseAmount,
            )

            return memberMoneyRepository.save(newMemberMoneyEntity)
        }

        entity.balance += increaseAmount
        return memberMoneyRepository.save(entity)
    }
}