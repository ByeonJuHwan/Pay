package com.dev.adapter.out.persistence

import com.dev.aplication.port.out.FindRegisterBankAccountPort
import com.dev.aplication.port.out.RegisterBankAccountPort
import com.dev.common.PersistenceAdapter
import com.dev.domain.RegisteredBankAccount

@PersistenceAdapter
class RegisteredBankAccountPersistenceAdapter(
    private val bankAccountRepository: SpringDataRegisteredBankAccountRepository,
) : RegisterBankAccountPort, FindRegisterBankAccountPort {
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

    override fun findById(bankAccountId: RegisteredBankAccount.RegisteredBankAccountId): RegisteredBankAccountJpaEntity {
        return bankAccountRepository.findById(bankAccountId.registeredBankAccountId.toLong()).orElseThrow()
    }
}