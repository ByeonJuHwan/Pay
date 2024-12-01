package com.dev.application.service

import com.dev.adapter.out.persistence.toDomain
import com.dev.application.port.`in`.IncreaseMoneyRequestUseCase
import com.dev.application.port.`in`.command.IncreaseMoneyRequestCommand
import com.dev.application.port.out.GetMembershipPort
import com.dev.application.port.out.IncreaseMoneyPort
import com.dev.application.port.out.SendRechargingMoneyTaskPort
import com.dev.common.CountDownLatchManager
import com.dev.common.KafkaStatus
import com.dev.common.RechargingMoneyTask
import com.dev.common.SubTask
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
    private val membershipPort: GetMembershipPort,
    private val sendRechargingMoneyTask: SendRechargingMoneyTaskPort,
    private val countDownLatchManager: CountDownLatchManager,
) : IncreaseMoneyRequestUseCase {
    override fun increaseMoney(command: IncreaseMoneyRequestCommand): MoneyChangingRequest {

        // 머니의 충전, 증액이라는 과정
        // 1. 고객 정보가 정상인지 확인 (멤버)
        membershipPort.getMembership(command.targetMembershipId)
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

    override fun increaseMoneyAsync(command: IncreaseMoneyRequestCommand): MoneyChangingRequest? {

        // SubTask
        // 각 서비스에서 특정 membershipId 로 Validation 을 하기 위한 Task
        countDownLatchManager.addCountDownLatch("rechargingMoneyTask")

        // 1. SubTask, Task
        val validMemberTask = SubTask(
            command.targetMembershipId,
            "validMemberTask : 멤버십 유효성 검사",
            "membership",
            KafkaStatus.READY.status
        )

        // Banking Sub Task
        // Banking Account Validation
        val validBankingAccountTask = SubTask(
            command.targetMembershipId,
            "validBankingAccountTask : 뱅킹 계좌 유효성 검사",
            "banking",
            KafkaStatus.READY.status
        )

        // Amount Money Firmbanking --> 무조건 Ok 받았다고 가정

        val subTaskList = listOf(
            validMemberTask,
            validBankingAccountTask,
        )

        val task = RechargingMoneyTask(
            UUID.randomUUID().toString(),
            "Increase Money Task / 머니 충전 Task",
            command.targetMembershipId,
            subTaskList,
            "ByeonBank",
            "111-1111-1111",
            command.amount
        )

        // 2. Kafka Cluster Produce
        sendRechargingMoneyTask.sendRechargingMoneyTaskPort(task)


        // 3. Wait
        countDownLatchManager.getCountDownLatch("rechargingMoneyTask")?.await()

        // 3-1 task-consumer
        // 등록된 sub-task, status 모두 ok -> task 결과를 produce
        // 4. Task Result Consume
        val result = countDownLatchManager.getDataForKey(task.taskId)


        if(result == KafkaStatus.SUSSES.status) {
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
        } else {
            return null
        }
    }
}