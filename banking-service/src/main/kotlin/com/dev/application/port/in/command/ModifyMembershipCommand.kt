package com.dev.application.port.`in`.command

import com.dev.common.SelfValidating
import org.jetbrains.annotations.NotNull

data class ModifyMembershipCommand(
    @field:NotNull
    val membershipId: String,

    @field:NotNull
    val name: String,

    @field:NotNull
    val email: String,

    @field:NotNull
    val address: String,

    @field:NotNull
    val isValid: Boolean,

    val isCorp: Boolean
) : SelfValidating<ModifyMembershipCommand>()
