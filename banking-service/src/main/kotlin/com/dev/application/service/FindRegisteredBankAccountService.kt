package com.dev.application.service

import com.dev.adapter.out.persistence.entity.toDomain
import com.dev.application.port.`in`.FindBankAccountUseCase
import com.dev.application.port.`in`.command.FindRegisteredBankAccountCommand
import com.dev.application.port.out.FindRegisterBankAccountPort
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