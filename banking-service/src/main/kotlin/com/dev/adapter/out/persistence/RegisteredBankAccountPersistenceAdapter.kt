package com.dev.adapter.out.persistence

import com.dev.aplication.port.out.RegisterBankAccountPort
import com.dev.common.PersistenceAdapter
import com.dev.domain.RegisteredBankAccount

@PersistenceAdapter
class RegisteredBankAccountPersistenceAdapter(
    private val bankAccountRepository: SpringDataRegisteredBankAccountRepository,
) : RegisterBankAccountPort {
    override fun createBankAccount(
        membershipId: RegisteredBankAccount.MembershipId,
        bankName: RegisteredBankAccount.BankName,
        bankAccountNumber: RegisteredBankAccount.BankAccountNumber,
        linkedStatusIsValid: RegisteredBankAccount.LinkedStatusIsValid
    ): RegisteredBankAccountJpaEntity {
        return bankAccountRepository.save(
            RegisteredBankAccountJpaEntity(
                membershipId = membershipId.membershipId,
                bankName = bankName.bankName,
                bankAccountNumber = bankAccountNumber.bankAccountNumber,
                linkedStatusIsValid = linkedStatusIsValid.linkedStatusIsValid,
            )
        )
    }
}