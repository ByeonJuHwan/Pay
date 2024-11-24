package com.dev.application.service

import com.dev.application.port.`in`.IncreaseMoneyRequestUseCase
import com.dev.application.port.`in`.command.IncreaseMoneyRequestCommand
import com.dev.domain.MoneyChangingRequest
import org.springframework.stereotype.Service

@Service
class IncreaseMoneyRequestService (

) : IncreaseMoneyRequestUseCase {
    override fun increaseMoney(command: IncreaseMoneyRequestCommand): MoneyChangingRequest {
        TODO("Not yet implemented")
    }
}