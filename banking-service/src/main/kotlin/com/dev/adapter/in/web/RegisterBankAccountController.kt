package com.dev.adapter.`in`.web

import com.dev.adapter.`in`.web.request.RegisterBankAccountRequest
import com.dev.application.port.`in`.command.RegisterBankAccountCommand
import com.dev.application.port.`in`.RegisterBankAccountUseCase
import com.dev.common.WebAdapter
import com.dev.domain.RegisteredBankAccount
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@WebAdapter
@RestController
class RegisterBankAccountController(
    private val registerBankAccountUseCase: RegisterBankAccountUseCase,
) {

    @PostMapping("/bank-accounts")
    fun registerBankAccount(
        @RequestBody request: RegisterBankAccountRequest,
    ) : RegisteredBankAccount {

        val command = RegisterBankAccountCommand(
            membershipId = request.membershipId,
            bankName = request.bankName,
            bankAccountNumber = request.bankAccountNumber,
            isValid = request.isValid,
        )

        return registerBankAccountUseCase.registerBankAccount(command)
    }
}