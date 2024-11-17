package com.dev.aplication.port.`in`

import com.dev.aplication.port.`in`.command.FindMembershipCommand
import com.dev.common.UseCase
import com.dev.domain.Membership

@UseCase
interface FindMembershipUseCase {

    fun findMembership(command: FindMembershipCommand) : Membership
}