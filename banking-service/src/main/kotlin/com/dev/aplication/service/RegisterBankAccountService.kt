package com.dev.aplication.service

import com.dev.adapter.out.external.bank.GetBankAccountRequest
import com.dev.adapter.out.persistence.entity.toDomain
import com.dev.aplication.port.`in`.command.RegisterBankAccountCommand
import com.dev.aplication.port.`in`.RegisterBankAccountUseCase
import com.dev.aplication.port.out.RegisterBankAccountPort
import com.dev.aplication.port.out.RequestBankAccountInfoPort
import com.dev.domain.RegisteredBankAccount
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional
class RegisterBankAccountService (
    private val registerBankAccountPort: RegisterBankAccountPort,
    private val requestBankAccountInfoPort: RequestBankAccountInfoPort,
) : RegisterBankAccountUseCase {

    override fun registerBankAccount(command: RegisterBankAccountCommand): RegisteredBankAccount {
        // 은행 계좌를 등록해야 하는 서비스 (비즈니스 로직)

        // (멤버 서비스도 확인?) 여기서는 일단 skip

        // 1. 외부 실제 은행에 등록된 계좌인지(정상인지) 확인한다.
        // 외부의 은행에 이 계좌 정상인지? 확인
        // Biz Logic -> External System
        // Port -> Adapter -> External System
        // Port
        // 실제 외부의 은행계좌 정보를 Get
        val request = GetBankAccountRequest(command.bankName, command.bankAccountNumber)
        val accountInfo = requestBankAccountInfoPort.getBankAccountInfo(request)
        val accountIsValid = accountInfo.isValid

        // 2. 등록가능한 계좌라면, 등록한다. 성공하면, 등록에 성공한 등록 정보를 리턴
        // 2-1. 등록하지 않은 계좌라면. 에러를 리턴
        validateBankAccount(accountIsValid)

        return registerBankAccountPort.createRegisteredBankAccount(
            RegisteredBankAccount.MembershipId(command.membershipId),
            RegisteredBankAccount.BankName(command.bankName),
            RegisteredBankAccount.BankAccountNumber(command.bankAccountNumber),
            RegisteredBankAccount.LinkedStatusIsValid(command.isValid),
        ).toDomain()
    }

    private fun validateBankAccount(accountIsValid: Boolean) {
        require(accountIsValid) {throw RuntimeException("올바른 은행계좌가 아닙니다")}
    }
}