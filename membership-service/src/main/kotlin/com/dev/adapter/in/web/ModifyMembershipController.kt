package com.dev.adapter.`in`.web

import com.dev.adapter.`in`.web.request.ModifyMembershipRequest
import com.dev.aplication.port.`in`.command.ModifyMembershipCommand
import com.dev.aplication.port.`in`.ModifyMembershipUseCase
import com.dev.domain.Membership
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ModifyMembershipController (
    private val modifyMembershipUseCase: ModifyMembershipUseCase
) {

    @PostMapping("/membership/modify/{membershipId}")
    fun modifyMembershipByMemberId(
        @PathVariable membershipId: String,
        @RequestBody request: ModifyMembershipRequest
    ) : ResponseEntity<Membership> {
        val command = ModifyMembershipCommand(
            request.membershipId,
            request.name,
            request.email,
            request.address,
            request.isValid,
            request.isCorp
        )
        return ResponseEntity.ok(modifyMembershipUseCase.modifyMembership(command))
    }
}