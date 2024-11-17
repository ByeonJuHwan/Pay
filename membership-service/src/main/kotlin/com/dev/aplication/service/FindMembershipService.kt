package com.dev.aplication.service

import com.dev.adapter.out.persistence.toDomain
import com.dev.aplication.port.`in`.RegisterMembershipCommand
import com.dev.aplication.port.`in`.RegisterMembershipUseCase
import com.dev.aplication.port.out.RegisterMembershipPort
import com.dev.domain.Membership
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional
class RegisterMembershipService (
    private val registerMembershipPort: RegisterMembershipPort,
) : RegisterMembershipUseCase {


    override fun registerMembership(command: RegisterMembershipCommand) : Membership {

        val membership = registerMembershipPort.createMembership(
            Membership.MembershipName(command.name),
            Membership.MembershipEmail(command.email),
            Membership.MembershipAddress(command.address),
            Membership.MembershipIsValid(command.isValid),
            Membership.MembershipIsCorp(command.isCorp)
        ).toDomain()

        return membership
    }
}