package com.dev.application.service

import com.dev.adapter.out.external.bank.ExternalFirmbankingRequest
import com.dev.adapter.out.persistence.entity.toDomain
import com.dev.application.port.`in`.RequestFrimbankingUseCase
import com.dev.application.port.`in`.command.FirmBankingRequestCommand
import com.dev.application.port.out.RequestExternalFirmbankingPort
import com.dev.application.port.out.RequestFirmbankingPort
import com.dev.domain.FirmBankingRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class RequestFirmbankingService (
    private val requestFirmbankingPort: RequestFirmbankingPort,
    private val requestExternalFirmbankingPort: RequestExternalFirmbankingPort,
) : RequestFrimbankingUseCase {

    override fun requestFrimbanking(command: FirmBankingRequestCommand): FirmBankingRequest {

        // a -> b 계좌

        // 1. 요청에 대한정보를 먼저 write. "요청"  tkdxofh
        val requestedEntity = requestFirmbankingPort.createFirmbankingRequest(
            fromBankName = FirmBankingRequest.FromBankName(command.fromBankName),
            fromBankAccount = FirmBankingRequest.FromBankAccountNumber(command.fromBankAccountNumber),
            toBankName = FirmBankingRequest.ToBankName(command.toBankName),
            toBankAccountNumber = FirmBankingRequest.ToBankAccountNumber(command.toBankAccountNumber),
            moneyAmount = FirmBankingRequest.MoneyAmount(command.moneyAmount),
            firmBankingStatus = FirmBankingRequest.FirmBankingStatus(0)
        )

        // 2. 외부 은행에 펌뱅킹 요청
        val request = ExternalFirmbankingRequest(
            command.fromBankName,
            command.fromBankAccountNumber,
            command.toBankName,
            command.toBankAccountNumber,
        )
        val result = requestExternalFirmbankingPort.requestExternalFirmbanking(request)

        val randomUUID = UUID.randomUUID()
        requestedEntity.uuid = randomUUID

        // 3. 결과에 따라서 1번에서 작성했던 FirmbankingRequest 정보를 Update
        if (result.resultCode == 0) {
            requestedEntity.firmBankingStatus = 1
        } else {
            requestedEntity.firmBankingStatus = 2
        }

        // 4. 결과를 리턴하기전에 바뀐값 저장
        return requestFirmbankingPort.modifyFirmbankingRequest(requestedEntity).toDomain()
    }
}