package com.dev.adapter.out.external.bank

import com.dev.application.port.out.RequestBankAccountInfoPort
import com.dev.application.port.out.RequestExternalFirmbankingPort
import com.dev.common.ExternalSystemAdapter

@ExternalSystemAdapter
class BankAccountAdapter : RequestBankAccountInfoPort, RequestExternalFirmbankingPort {

    /**
     * 실제로 외부 은행에 http 요청을 통해서
     * 실제 은행 계좌 정보를 가져오고
     *
     * 실제 은행 계좌 -> BankAccount
     */
    override fun getBankAccountInfo(request: GetBankAccountRequest): BankAccount {
        return BankAccount(
            request.bankName,
            request.bankAccountNumber,
            true,
        )
    }

    /**
     * 실제 외부 은행에 http 통신을 통해서 펌뱅킹을 요청을 하고
     *
     * 그 결과를 외부 은행의 실제 결과를 -> FirmbankingResult 파싱
     */
    override fun requestExternalFirmbanking(request: ExternalFirmbankingRequest): FirmbankingResult {
        return FirmbankingResult(0)
    }
}