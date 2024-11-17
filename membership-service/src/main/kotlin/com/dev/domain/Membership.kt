package com.dev.domain

class Membership private constructor(
    val membershipId: String,
    val name: String,
    val email: String,
    val address: String,
    val isValid: Boolean,
    val isCorp : Boolean,
) {
    data class MembershipId(
        val membershipId: String
    )

    data class MembershipName(
        val nameValue: String
    )

    data class MembershipEmail(
        val emailValue: String
    )

    data class MembershipAddress(
        val addressValue: String
    )

    data class MembershipIsValid(
        val isValidValue: Boolean
    )

    data class MembershipIsCorp(
        val isCorpValue: Boolean
    )

    companion object {
        fun generateMember(
            membershipId: MembershipId,
            membershipName: MembershipName,
            membershipEmail: MembershipEmail,
            membershipAddress: MembershipAddress,
            membershipIsValid: MembershipIsValid,
            membershipIsCorp: MembershipIsCorp,
        ): Membership {
            return Membership(
                membershipId = membershipId.membershipId,
                name = membershipName.nameValue,
                email = membershipEmail.emailValue,
                address = membershipAddress.addressValue,
                isValid = membershipIsValid.isValidValue,
                isCorp = membershipIsCorp.isCorpValue,
            )
        }
    }
}