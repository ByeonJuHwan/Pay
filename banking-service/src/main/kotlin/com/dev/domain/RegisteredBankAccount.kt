package com.dev.domain

class RegisteredBankAccount private constructor(
    val registeredBankAccountId: String,
    val membershipId: String,
    val bankName: String,
    val bankAccountNumber: String,
    val linkedStatusIsValid: Boolean,
) {
    data class RegisteredBankAccountId(
        val registeredBankAccountId: String
    )

    data class MembershipId(
        val membershipId: String
    )

    data class BankName(
        val bankName: String
    )

    data class BankAccountNumber(
        val bankAccountNumber: String
    )

    data class LinkedStatusIsValid(
        val linkedStatusIsValid: Boolean
    )

    companion object {
        fun generateRegisteredBankAccount(
            registeredBankAccountId: RegisteredBankAccountId,
            membershipId: MembershipId,
            bankName: BankName,
            bankAccountNumber: BankAccountNumber,
            linkedStatusIsValid: LinkedStatusIsValid,
        ): RegisteredBankAccount {
            return RegisteredBankAccount(
                registeredBankAccountId = registeredBankAccountId.registeredBankAccountId,
                membershipId = membershipId.membershipId,
                bankName = bankName.bankName,
                bankAccountNumber = bankAccountNumber.bankAccountNumber,
                linkedStatusIsValid = linkedStatusIsValid.linkedStatusIsValid,
            )
        }
    }
}