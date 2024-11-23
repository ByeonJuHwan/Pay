package com.dev.adapter.out.external.bank

import com.dev.aplication.port.out.RequestBankAccountInfoPort
import com.dev.common.ExternalSystemAdapter

@ExternalSystemAdapter
class BankAccountAdapter : RequestBankAccountInfoPort {

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
}