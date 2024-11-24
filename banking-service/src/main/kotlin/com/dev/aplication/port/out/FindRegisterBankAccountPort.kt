package com.dev.aplication.port.out

import com.dev.adapter.out.persistence.entity.RegisteredBankAccountJpaEntity
import com.dev.domain.RegisteredBankAccount

interface FindRegisterBankAccountPort {
    fun findById (bankAccountId: RegisteredBankAccount.RegisteredBankAccountId): RegisteredBankAccountJpaEntity
}