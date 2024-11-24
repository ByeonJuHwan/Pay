package com.dev.application.port.`in`

import com.dev.application.port.`in`.command.FirmBankingRequestCommand
import com.dev.common.UseCase
import com.dev.domain.FirmBankingRequest

@UseCase
interface RequestFrimbankingUseCase {
    fun requestFrimbanking(command: FirmBankingRequestCommand): FirmBankingRequest
}