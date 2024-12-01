package com.dev.application.port.out

import com.dev.adapter.out.service.MembershipStatus

interface GetMembershipPort {

    fun getMembership(membershipId: String) : MembershipStatus
}