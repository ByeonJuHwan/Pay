package com.dev.adapter.out.persistence

import com.dev.domain.MoneyChangingRequest
import com.dev.domain.type.MoneyChangingStatus
import com.dev.domain.type.MoneyChangingType
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal
import java.util.*

@Entity
@Table(name = "money_changing_request")
class MoneyChangingRequestJpaEntity (

    var targetMembershipId: String,
    @Enumerated(EnumType.STRING)
    var moneyChangingType: MoneyChangingType,
    var moneyAmount: BigDecimal,
    var changingMoneyStatus: MoneyChangingStatus,
    val uuid: String,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val moneyChangingRequestId: Long? = null,
) {
}

fun MoneyChangingRequestJpaEntity.toDomain(): MoneyChangingRequest {
    return MoneyChangingRequest.generateMoneyChangingRequest(
        moneyChangingRequestId = MoneyChangingRequest.MoneyChangingRequestId(this.moneyChangingRequestId.toString()),
        targetMembershipId = MoneyChangingRequest.TargetMembershipId(this.targetMembershipId),
        changingType = this.moneyChangingType,
        changingMoneyAmount = MoneyChangingRequest.ChangingMoneyAmount(this.moneyAmount),
        changingMoneyStatus = this.changingMoneyStatus,
        uuid = MoneyChangingRequest.Uuid(UUID.fromString(this.uuid)),
    )
}