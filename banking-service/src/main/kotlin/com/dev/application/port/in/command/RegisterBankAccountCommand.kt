package com.dev.application.port.`in`.command

import com.dev.common.SelfValidating
import org.jetbrains.annotations.NotNull

data class RegisterBankAccountCommand(
    @field:NotNull
    val membershipId: String,

    @field:NotNull
    val bankName: String,

    @field:NotNull
    val bankAccountNumber: String,

    val isValid: Boolean,
) : SelfValidating<RegisterBankAccountCommand>()
