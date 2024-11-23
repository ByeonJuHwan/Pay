package com.dev.aplication.service

import com.dev.adapter.out.persistence.toDomain
import com.dev.aplication.port.`in`.command.RegisterBankAccountCommand
import com.dev.aplication.port.`in`.RegisterBankAccountUseCase
import com.dev.aplication.port.out.RegisterBankAccountPort
import com.dev.domain.RegisteredBankAccount
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional
class RegisterBankAccountService (
    private val registerBankAccountPort: RegisterBankAccountPort,
) : RegisterBankAccountUseCase {

    override fun registerBankAccount(command: RegisterBankAccountCommand): RegisteredBankAccount {
        val bankAccount = registerBankAccountPort.createBankAccount(
            RegisteredBankAccount.MembershipId(command.membershipId),
            RegisteredBankAccount.BankName(command.bankName),
            RegisteredBankAccount.BankAccountNumber(command.bankAccountNumber),
            RegisteredBankAccount.LinkedStatusIsValid(command.isValid),
        ).toDomain()

        return bankAccount
    }
}