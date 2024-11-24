package com.dev.adapter.`in`.web

import com.dev.adapter.`in`.web.request.RequestFirmbankingRequest
import com.dev.application.port.`in`.RequestFrimbankingUseCase
import com.dev.application.port.`in`.command.FirmBankingRequestCommand
import com.dev.domain.FirmBankingRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class RequestFirmbankingController (
    private val requestFrimbankingUseCase: RequestFrimbankingUseCase,
) {

    @PostMapping("/bank-accounts/frimbanking")
    fun registerBankAccount(
        @RequestBody request: RequestFirmbankingRequest,
    ) : FirmBankingRequest {

        val command = FirmBankingRequestCommand(
            fromBankName = request.fromBankName,
            fromBankAccountNumber = request.fromBankAccountNumber,
            toBankName = request.toBankName,
            toBankAccountNumber = request.toBankAccountNumber,
            moneyAmount = request.moneyAmount,
        )

        return requestFrimbankingUseCase.requestFrimbanking(command)
    }
}