package com.dev.aplication.port.`in`

import com.dev.aplication.port.`in`.command.RegisterBankAccountCommand
import com.dev.common.UseCase
import com.dev.domain.RegisteredBankAccount

@UseCase
interface RegisterBankAccountUseCase {

    fun registerBankAccount(command: RegisterBankAccountCommand) : RegisteredBankAccount
}