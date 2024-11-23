package com.dev.aplication.service

import com.dev.adapter.out.persistence.toDomain
import com.dev.aplication.port.`in`.FindBankAccountUseCase
import com.dev.aplication.port.`in`.command.FindRegisteredBankAccountCommand
import com.dev.aplication.port.out.FindRegisterBankAccountPort
import com.dev.domain.RegisteredBankAccount
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class FindRegisteredBankAccountService (
    private val findRegisterBankAccountPort: FindRegisterBankAccountPort,
) : FindBankAccountUseCase {

    override fun findBankAccounts(command: FindRegisteredBankAccountCommand): RegisteredBankAccount {
        return findRegisterBankAccountPort.findById(
            RegisteredBankAccount.RegisteredBankAccountId(command.registeredBankAccountId)
        ).toDomain()
    }
}