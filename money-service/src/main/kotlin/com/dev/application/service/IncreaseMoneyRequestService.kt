package com.dev.application.service

import com.dev.adapter.out.persistence.toDomain
import com.dev.application.port.`in`.IncreaseMoneyRequestUseCase
import com.dev.application.port.`in`.command.IncreaseMoneyRequestCommand
import com.dev.application.port.out.IncreaseMoneyPort
import com.dev.domain.MemberMoney
import com.dev.domain.MoneyChangingRequest
import com.dev.domain.type.MoneyChangingStatus
import com.dev.domain.type.MoneyChangingType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class IncreaseMoneyRequestService (
    private val increaseMoneyPort: IncreaseMoneyPort,
) : IncreaseMoneyRequestUseCase {
    override fun increaseMoney(command: IncreaseMoneyRequestCommand): MoneyChangingRequest {

        // 머니의 충전, 증액이라는 과정
        // 1. 고객 정보가 정상인지 확인 (멤버)
        // 2. 고객의 연동된 걔좌가 있는지, 고객의 연동된 계좌의 잔액이 충분한지도 확인 (뱅킹)
        // 3. 법인 계좌 상태도 정상인지 확인 (뱅킹)
        // 4. 증액을 위핸 "기록". 요청 상태로 MoneyChangingRequest 를 생성한다. (MoneyChangingRequest)
        // 5. 펌뱅킹을 수행하고 ( 고객의 연동된 계좌 -> ~페이 볍인 계좌 (뱅킹)
        // 6-1. 결과가 정상적이라면, 성공으로 MoneyChangingRequest 상태값을 변동 후에 리턴
        // 성공 시에 멤버의 MemberMoney 값 증액 필요
        increaseMoneyPort.increaseMoney(
            MemberMoney.MembershipId(command.targetMembershipId),
            command.amount,
        )

        return increaseMoneyPort.createMoneyChangingRequest(
            MoneyChangingRequest.TargetMembershipId(command.targetMembershipId),
            MoneyChangingRequest.ChangingType(MoneyChangingType.INCREASING),
            MoneyChangingRequest.ChangingMoneyAmount(command.amount),
            MoneyChangingRequest.ChangingMoneyStatus(MoneyChangingStatus.SUCCEEDED),
            MoneyChangingRequest.Uuid(UUID.randomUUID())
        ).toDomain()
        // 6-2. 결과가 실패라면, 실패마로 MoneyChangingRequest 상태값을 변동 후에 리턴

    }
}