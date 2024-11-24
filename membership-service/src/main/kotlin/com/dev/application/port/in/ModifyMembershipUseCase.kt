package com.dev.application.port.`in`

import com.dev.application.port.`in`.command.ModifyMembershipCommand
import com.dev.domain.Membership

interface ModifyMembershipUseCase {
    fun modifyMembership(command: ModifyMembershipCommand) : Membership
}