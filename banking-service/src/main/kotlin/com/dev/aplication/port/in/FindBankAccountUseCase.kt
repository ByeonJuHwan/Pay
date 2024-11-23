package com.dev.aplication.port.`in`

import com.dev.aplication.port.`in`.command.FindRegisteredBankAccountCommand
import com.dev.domain.RegisteredBankAccount

interface FindBankAccountUseCase {
    fun findBankAccounts(command: FindRegisteredBankAccountCommand): RegisteredBankAccount
}