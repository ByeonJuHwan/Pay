package com.dev.aplication.port.`in`

import com.dev.common.SelfValidating
import org.jetbrains.annotations.NotNull

data class FindMembershipCommand(
    @field:NotNull
    val membershipId : String,
) : SelfValidating<FindMembershipCommand>()
