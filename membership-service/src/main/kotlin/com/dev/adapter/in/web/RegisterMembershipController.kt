package com.dev.adapter.`in`.web

import com.dev.adapter.`in`.web.request.RegisterMembershipRequest
import com.dev.aplication.port.`in`.command.RegisterMembershipCommand
import com.dev.aplication.port.`in`.RegisterMembershipUseCase
import com.dev.common.WebAdapter
import com.dev.domain.Membership
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@WebAdapter
@RestController
class RegisterMembershipController (
    private val registerMembershipUseCase: RegisterMembershipUseCase,
) {

    @PostMapping("/membership/register")
    fun registerMembership(
        @RequestBody request: RegisterMembershipRequest,
    ) : Membership {

        val command = RegisterMembershipCommand(
            name = request.name,
            email = request.email,
            address = request.address,
            isValid = true,
            isCorp = request.isCorp
        )

        return registerMembershipUseCase.registerMembership(command)
    }
}