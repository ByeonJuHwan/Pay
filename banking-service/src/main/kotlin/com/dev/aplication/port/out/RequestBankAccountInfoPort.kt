package com.dev.aplication.port.out

import com.dev.adapter.out.external.bank.BankAccount
import com.dev.adapter.out.external.bank.GetBankAccountRequest

interface RequestBankAccountInfoPort {
    fun getBankAccountInfo(request: GetBankAccountRequest) : BankAccount
}