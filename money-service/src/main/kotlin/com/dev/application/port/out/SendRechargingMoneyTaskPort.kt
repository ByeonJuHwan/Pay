package com.dev.application.port.out

import com.dev.common.RechargingMoneyTask

interface SendRechargingMoneyTaskPort {

    fun sendRechargingMoneyTaskPort(task: RechargingMoneyTask)
}