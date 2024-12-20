package com.dev.adapter.`in`.web

import com.dev.application.port.`in`.command.FindMembershipCommand
import com.dev.application.port.`in`.FindMembershipUseCase
import com.dev.domain.Membership
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class FindMembershipController (
    private val findMembershipUseCase: FindMembershipUseCase
) {

    @GetMapping("/memberships/{membershipId}")
    fun findMembershipByMemberId(
        @PathVariable membershipId: String,
    ) : Membership {
        val command = FindMembershipCommand(membershipId)
        return findMembershipUseCase.findMembership(command)
    }
}
