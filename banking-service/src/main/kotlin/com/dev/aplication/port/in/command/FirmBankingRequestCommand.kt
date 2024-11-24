package com.dev.aplication.port.`in`.command

import com.dev.common.SelfValidating
import org.jetbrains.annotations.NotNull
import java.math.BigDecimal

data class FirmBankingRequestCommand(
    @field:NotNull
    val fromBankName: String,
    @field:NotNull
    val fromBankAccountNumber: String,
    @field:NotNull
    val toBankName: String,
    @field:NotNull
    val toBankAccountNumber: String,
    val moneyAmount: BigDecimal,
) : SelfValidating<ModifyMembershipCommand>()
