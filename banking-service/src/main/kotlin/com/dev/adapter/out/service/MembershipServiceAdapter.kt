package com.dev.adapter.out.service

import com.dev.application.port.out.GetMembershipPort
import com.dev.common.CommonHttpClient
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class MembershipServiceAdapter (
    private val commonHttpClient: CommonHttpClient,
    @Value("\${service.membership.url}")private val membershipServiceUrl: String,
    private val mapper: ObjectMapper
) : GetMembershipPort {

    override fun getMembership(membershipId: String): MembershipStatus {
        // 실제로 http call
        // http Client

        val url = "${membershipServiceUrl}/memberships/$membershipId"
        try {
            val jsonResponse = commonHttpClient.sendGetRequest(url).body()

            val membership = mapper.readValue(jsonResponse, Membership::class.java)

            return if (membership.isValid) {
                MembershipStatus(membership.membershipId, true)
            } else {
                MembershipStatus(membership.membershipId, false)
            }

        } catch (e: Exception) {
            throw RuntimeException("Error getting membership", e)
        }
    }
}