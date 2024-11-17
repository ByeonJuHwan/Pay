package com.dev.aplication.service

import com.dev.adapter.out.persistence.toDomain
import com.dev.aplication.port.`in`.command.ModifyMembershipCommand
import com.dev.aplication.port.`in`.ModifyMembershipUseCase
import com.dev.aplication.port.out.ModifyMembershipPort
import com.dev.domain.Membership
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional
class ModifyMembershipService (
    private val modifyMembershipPort: ModifyMembershipPort,
) : ModifyMembershipUseCase {

    override fun modifyMembership(command: ModifyMembershipCommand): Membership {
        val membership = modifyMembershipPort.modifyMembership(
            Membership.MembershipId(command.membershipId),
            Membership.MembershipName(command.name),
            Membership.MembershipEmail(command.email),
            Membership.MembershipAddress(command.address),
            Membership.MembershipIsValid(command.isValid),
            Membership.MembershipIsCorp(command.isCorp)
        ).toDomain()

        return membership
    }
}