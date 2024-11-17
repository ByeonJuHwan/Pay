package com.dev.aplication.port.`in`

import com.dev.aplication.port.`in`.command.RegisterMembershipCommand
import com.dev.common.UseCase
import com.dev.domain.Membership

@UseCase
interface RegisterMembershipUseCase {

    fun registerMembership(command: RegisterMembershipCommand) : Membership
}