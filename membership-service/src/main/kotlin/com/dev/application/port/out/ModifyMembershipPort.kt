package com.dev.application.port.out

import com.dev.adapter.out.persistence.MembershipJpaEntity
import com.dev.domain.Membership

interface ModifyMembershipPort {
    fun modifyMembership (
        membershipId: Membership.MembershipId,
        membershipName: Membership.MembershipName,
        membershipEmail: Membership.MembershipEmail,
        membershipAddress: Membership.MembershipAddress,
        membershipIsValid: Membership.MembershipIsValid,
        membershipIsCorp: Membership.MembershipIsCorp,
    ) :  MembershipJpaEntity
}