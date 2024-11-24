package com.dev.adapter.out.persistence

import com.dev.application.port.out.FindMembershipPort
import com.dev.application.port.out.ModifyMembershipPort
import com.dev.application.port.out.RegisterMembershipPort
import com.dev.common.PersistenceAdapter
import com.dev.domain.Membership

@PersistenceAdapter
class MembershipPersistenceAdapter (
    private val membershipRepository: SpringDataMembershipRepository,
) : RegisterMembershipPort, FindMembershipPort, ModifyMembershipPort {

    override fun createMembership(
        membershipName: Membership.MembershipName,
        membershipEmail: Membership.MembershipEmail,
        membershipAddress: Membership.MembershipAddress,
        membershipIsValid: Membership.MembershipIsValid,
        membershipIsCorp: Membership.MembershipIsCorp
    ) : MembershipJpaEntity {
        return membershipRepository.save(
            MembershipJpaEntity(
                membershipName.nameValue,
                membershipEmail.emailValue,
                membershipAddress.addressValue,
                membershipIsValid.isValidValue,
                membershipIsCorp.isCorpValue
            )
        )
    }


    override fun findById(membershipId: Membership.MembershipId): MembershipJpaEntity {
        return membershipRepository.findById(
            membershipId.membershipId.toLong()
        ).orElseThrow()
    }

    override fun modifyMembership(
        membershipId: Membership.MembershipId,
        membershipName: Membership.MembershipName,
        membershipEmail: Membership.MembershipEmail,
        membershipAddress: Membership.MembershipAddress,
        membershipIsValid: Membership.MembershipIsValid,
        membershipIsCorp: Membership.MembershipIsCorp
    ): MembershipJpaEntity {
        val entity = membershipRepository.findById(membershipId.membershipId.toLong()).orElseThrow()
        entity.modifyMembershipEntity(
            membershipName,
            membershipEmail,
            membershipAddress,
            membershipIsValid,
            membershipIsCorp,
        )
        return membershipRepository.save(entity)
    }
}