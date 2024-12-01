package com.dev.application.port.`in`

import com.dev.application.port.`in`.command.IncreaseMoneyRequestCommand
import com.dev.domain.MoneyChangingRequest

interface IncreaseMoneyRequestUseCase {
    fun increaseMoney(command: IncreaseMoneyRequestCommand): MoneyChangingRequest
    fun increaseMoneyAsync(command: IncreaseMoneyRequestCommand): MoneyChangingRequest?
}