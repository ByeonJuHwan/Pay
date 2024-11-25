package com.dev.adapter.out.persistence

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.math.BigDecimal

@Entity
class MemberMoneyJpaEntity (

    var membershipId: String,
    var balance: BigDecimal,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val memberMoneyId: Long? = null,
) {
}