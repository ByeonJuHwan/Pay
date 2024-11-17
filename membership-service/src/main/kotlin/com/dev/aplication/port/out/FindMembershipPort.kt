package com.dev.aplication.port.out

import com.dev.adapter.out.persistence.MembershipJpaEntity
import com.dev.domain.Membership

interface FindMembershipPort {

    fun findById (
        membershipId: Membership.MembershipId
    ) :  MembershipJpaEntity
}