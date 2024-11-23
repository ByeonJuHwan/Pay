package com.dev.adapter.`in`.web.request

data class ModifyMembershipRequest(
    val membershipId : String,
    val name : String,
    val address : String,
    val email : String,
    val isValid : Boolean,
    val isCorp : Boolean,
)
