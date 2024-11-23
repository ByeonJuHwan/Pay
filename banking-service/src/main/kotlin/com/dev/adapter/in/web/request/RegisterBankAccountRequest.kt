package com.dev.adapter.`in`.web.request

data class RegisterBankAccountRequest(
    val membershipId : String,
    val bankName : String,
    val bankAccountNumber : String,
    val isValid : Boolean,
)
