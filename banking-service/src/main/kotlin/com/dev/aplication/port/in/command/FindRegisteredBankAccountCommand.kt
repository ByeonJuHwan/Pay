package com.dev.aplication.port.`in`.command

import com.dev.common.SelfValidating
import org.jetbrains.annotations.NotNull

data class FindRegisteredBankAccountCommand(
    @field:NotNull
    val registeredBankAccountId : String,
) : SelfValidating<FindRegisteredBankAccountCommand>()
