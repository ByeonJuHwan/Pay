package com.dev.aplication.port.`in`

import com.dev.aplication.port.`in`.command.ModifyMembershipCommand
import com.dev.domain.Membership

interface ModifyMembershipUseCase {
    fun modifyMembership(command: ModifyMembershipCommand) : Membership
}