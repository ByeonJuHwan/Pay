package com.dev.aplication.port.out

import com.dev.adapter.out.persistence.MembershipJpaEntity
import com.dev.domain.Membership

interface RegisterMembershipPort {
    fun createMembership (
        membershipName: Membership.MembershipName,
        membershipEmail: Membership.MembershipEmail,
        membershipAddress: Membership.MembershipAddress,
        membershipIsValid: Membership.MembershipIsValid,
        membershipIsCorp: Membership.MembershipIsCorp,
    ) :  MembershipJpaEntity
}