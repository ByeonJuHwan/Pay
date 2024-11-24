package com.dev.application.port.out

import com.dev.adapter.out.persistence.entity.RegisteredBankAccountJpaEntity
import com.dev.domain.RegisteredBankAccount


interface RegisterBankAccountPort {
    fun createRegisteredBankAccount (
        membershipId: RegisteredBankAccount.MembershipId,
        bankName: RegisteredBankAccount.BankName,
        bankAccountNumber: RegisteredBankAccount.BankAccountNumber,
        linkedStatusIsValid: RegisteredBankAccount.LinkedStatusIsValid,
    ) : RegisteredBankAccountJpaEntity
}