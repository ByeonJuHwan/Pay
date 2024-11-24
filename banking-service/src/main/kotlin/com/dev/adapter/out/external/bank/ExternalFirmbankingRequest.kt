package com.dev.adapter.out.external.bank

data class ExternalFirmbankingRequest (
    val fromBankName: String,
    val fromBankAccountNumber: String,
    val toBankName: String,
    val toBankAccountNumber: String,
)