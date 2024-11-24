package com.dev.adapter.`in`.web

import com.dev.application.port.`in`.FindBankAccountUseCase
import com.dev.application.port.`in`.command.FindRegisteredBankAccountCommand
import com.dev.domain.RegisteredBankAccount
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class FindBankAccountController (
    private val findBankAccountUseCase: FindBankAccountUseCase,
) {

    @GetMapping("/bank-accounts/{registeredBankAccountId}")
    fun findBankAccounts(
        @PathVariable registeredBankAccountId: String,
    ): RegisteredBankAccount {
        val command = FindRegisteredBankAccountCommand(registeredBankAccountId)
        return findBankAccountUseCase.findBankAccounts(command)
    }
}