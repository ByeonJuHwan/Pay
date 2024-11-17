package com.dev.aplication.port.`in`

import com.dev.comon.UseCase
import com.dev.domain.Membership

@UseCase
interface RegisterMembershipUseCase {

    fun registerMembership(command: RegisterMembershipCommand) : Membership
}