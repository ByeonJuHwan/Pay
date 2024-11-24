package com.dev.application.service

import com.dev.adapter.out.persistence.toDomain
import com.dev.application.port.`in`.command.FindMembershipCommand
import com.dev.application.port.`in`.FindMembershipUseCase
import com.dev.application.port.out.FindMembershipPort
import com.dev.domain.Membership
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional
class FindMembershipService (
    private val findMembershipPort: FindMembershipPort,
) : FindMembershipUseCase {


    override fun findMembership(command: FindMembershipCommand): Membership {
        return findMembershipPort.findById(
            Membership.MembershipId(command.membershipId)
        ).toDomain()
    }
}