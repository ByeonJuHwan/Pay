package com.dev.application.port.`in`

import com.dev.application.port.`in`.command.FindRegisteredBankAccountCommand
import com.dev.domain.RegisteredBankAccount

interface FindBankAccountUseCase {
    fun findBankAccounts(command: FindRegisteredBankAccountCommand): RegisteredBankAccount
}