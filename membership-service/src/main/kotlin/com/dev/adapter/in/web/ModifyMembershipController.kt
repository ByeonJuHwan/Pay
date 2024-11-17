package com.dev.adapter.`in`.web

import com.dev.aplication.port.`in`.FindMembershipCommand
import com.dev.aplication.port.`in`.FindMembershipUseCase
import com.dev.domain.Membership
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class FindMembershipController (
    private val findMembershipUseCase: FindMembershipUseCase
) {

    @GetMapping("/membership/{membershipId}")
    fun findMembershipByMemberId(
        @PathVariable membershipId: String,
    ) : Membership {
        val command = FindMembershipCommand(membershipId)
        return findMembershipUseCase.findMembership(command)
    }
}
