package com.dev.aplication.port.out

import com.dev.adapter.out.persistence.RegisteredBankAccountJpaEntity
import com.dev.domain.RegisteredBankAccount


interface RegisterBankAccountPort {
    fun createBankAccount (
        membershipId: RegisteredBankAccount.MembershipId,
        bankName: RegisteredBankAccount.BankName,
        bankAccountNumber: RegisteredBankAccount.BankAccountNumber,
        linkedStatusIsValid: RegisteredBankAccount.LinkedStatusIsValid,
    ) :  RegisteredBankAccountJpaEntity
}