package com.dev.adapter.out.persistence

import com.dev.domain.Membership
import jakarta.persistence.*

@Entity
@Table(name = "membership")
class MembershipJpaEntity (
    var name: String,
    var email: String,
    var address: String,
    var isValid: Boolean,
    var isCorp : Boolean,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long? = null,
) {
    fun modifyMembershipEntity(
        membershipName: Membership.MembershipName,
        membershipEmail: Membership.MembershipEmail,
        membershipAddress: Membership.MembershipAddress,
        membershipIsValid: Membership.MembershipIsValid,
        membershipIsCorp: Membership.MembershipIsCorp
    ) {
        this.name = membershipName.nameValue
        this.email = membershipEmail.emailValue
        this.address = membershipAddress.addressValue
        this.isValid = membershipIsValid.isValidValue
        this.isCorp = membershipIsCorp.isCorpValue
    }
}

fun MembershipJpaEntity.toDomain(): Membership {
    return Membership.generateMember(
        membershipId = Membership.MembershipId(this.id.toString()),
        membershipName = Membership.MembershipName(this.name),
        membershipEmail = Membership.MembershipEmail(this.email),
        membershipAddress = Membership.MembershipAddress(this.address),
        membershipIsValid = Membership.MembershipIsValid(this.isValid),
        membershipIsCorp = Membership.MembershipIsCorp(this.isCorp)
    )
}