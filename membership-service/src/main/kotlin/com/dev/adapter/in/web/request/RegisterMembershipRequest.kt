package com.dev.adapter.`in`.web

data class RegisterMembershipRequest(
    val name : String,
    val address : String,
    val email : String,
    val isCorp : Boolean,
)
