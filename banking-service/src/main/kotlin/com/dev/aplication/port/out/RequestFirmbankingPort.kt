package com.dev.aplication.port.out

import com.dev.adapter.out.persistence.entity.FirmbankingJpaEntity
import com.dev.domain.FirmBankingRequest

interface RequestFirmbankingPort {
    fun createFirmbankingRequest(
        fromBankName: FirmBankingRequest.FromBankName,
        fromBankAccount: FirmBankingRequest.FromBankAccountNumber,
        toBankName: FirmBankingRequest.ToBankName,
        toBankAccountNumber: FirmBankingRequest.ToBankAccountNumber,
        moneyAmount: FirmBankingRequest.MoneyAmount,
        firmBankingStatus: FirmBankingRequest.FirmBankingStatus,
    ) : FirmbankingJpaEntity

    fun modifyFirmbankingRequest(entity: FirmbankingJpaEntity) : FirmbankingJpaEntity
}