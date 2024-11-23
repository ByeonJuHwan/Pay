package com.dev.adapter.out.persistence

import com.dev.domain.RegisteredBankAccount
import jakarta.persistence.*

@Entity
@Table(name = "registered_bank_account")
class RegisteredBankAccountJpaEntity (
    var membershipId: String,
    var bankName: String,
    var bankAccountNumber: String,
    var linkedStatusIsValid: Boolean,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val registeredBankAccountId : Long? = null,
) {

}

fun RegisteredBankAccountJpaEntity.toDomain(): RegisteredBankAccount {
    return RegisteredBankAccount.generateRegisteredBankAccount(
        registeredBankAccountId = RegisteredBankAccount.RegisteredBankAccountId(this.registeredBankAccountId.toString()),
        membershipId = RegisteredBankAccount.MembershipId(this.membershipId),
        bankName = RegisteredBankAccount.BankName(this.bankName),
        bankAccountNumber = RegisteredBankAccount.BankAccountNumber(this.bankAccountNumber),
        linkedStatusIsValid = RegisteredBankAccount.LinkedStatusIsValid(this.linkedStatusIsValid),
    )
}