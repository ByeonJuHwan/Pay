package com.dev.adapter.out.persistence

import com.dev.adapter.out.persistence.entity.FirmbankingJpaEntity
import com.dev.application.port.out.RequestFirmbankingPort
import com.dev.common.PersistenceAdapter
import com.dev.domain.FirmBankingRequest

@PersistenceAdapter
class FirmbankingRequestPersistenceAdapter(
    private val repository: SpringDataFirmbankingRequestRepository,
) : RequestFirmbankingPort {
    override fun createFirmbankingRequest(
        fromBankName: FirmBankingRequest.FromBankName,
        fromBankAccount: FirmBankingRequest.FromBankAccountNumber,
        toBankName: FirmBankingRequest.ToBankName,
        toBankAccountNumber: FirmBankingRequest.ToBankAccountNumber,
        moneyAmount: FirmBankingRequest.MoneyAmount,
        firmBankingStatus: FirmBankingRequest.FirmBankingStatus
    ): FirmbankingJpaEntity {
        return repository.save(
            FirmbankingJpaEntity (
                fromBankName = fromBankName.fromBankName,
                fromBankAccountNumber = fromBankAccount.fromBankAccountNumber,
                toBankName = toBankName.toBankName,
                toBankAccountNumber = toBankAccountNumber.toBankAccountNumber,
                moneyAmount = moneyAmount.moneyAmount.toString(),
                firmBankingStatus = firmBankingStatus.firmBankingStatus,
                null
            )
        )
    }

    override fun modifyFirmbankingRequest(entity: FirmbankingJpaEntity): FirmbankingJpaEntity {
        return repository.save(entity)
    }
}