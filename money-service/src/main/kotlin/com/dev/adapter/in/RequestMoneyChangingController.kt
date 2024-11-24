package com.dev.adapter.`in`

import com.dev.adapter.`in`.request.IncreaseMoneyChangingRequest
import com.dev.adapter.`in`.response.MoneyChangingResultDetail
import com.dev.application.port.`in`.IncreaseMoneyRequestUseCase
import com.dev.application.port.`in`.command.IncreaseMoneyRequestCommand
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class RequestMoneyChangingController (
    private val increaseMoneyRequestUseCase: IncreaseMoneyRequestUseCase,
) {

    @PostMapping("/requestMoneyChanging")
    fun increaseMoneyChangingRequest (
        @RequestBody request: IncreaseMoneyChangingRequest,
    ) : MoneyChangingResultDetail {
        val command = IncreaseMoneyRequestCommand(
            request.targetMembershipId,
            request.amount,
        )
        val moneyChangingRequest = increaseMoneyRequestUseCase.increaseMoney(command)
        return MoneyChangingResultDetail.from(moneyChangingRequest)
    }
}