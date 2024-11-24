package com.dev.adapter.out.persistence.entity

import com.dev.domain.FirmBankingRequest
import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "request_firmbanking")
class FirmbankingJpaEntity (
    var fromBankName: String,
    var fromBankAccountNumber: String,
    var toBankName: String,
    var toBankAccountNumber: String,
    var moneyAmount: String,
    var firmBankingStatus: Int,
    var uuid: UUID?,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val requestFirmbankingId : Long? = null,
) {

}

fun FirmbankingJpaEntity.toDomain(): FirmBankingRequest {
    return FirmBankingRequest.generateFirmBankingRequest(
        firmBankingRequestId = FirmBankingRequest.FirmBankingRequestId(this.requestFirmbankingId.toString()),
        fromBankName = FirmBankingRequest.FromBankName(this.fromBankName),
        fromBankAccountNumber = FirmBankingRequest.FromBankAccountNumber(this.fromBankAccountNumber),
        toBankName = FirmBankingRequest.ToBankName(this.toBankName),
        toBankAccountNumber = FirmBankingRequest.ToBankAccountNumber(this.toBankAccountNumber),
        moneyAmount = FirmBankingRequest.MoneyAmount(this.moneyAmount.toBigDecimal()),
        firmBankingStatus = FirmBankingRequest.FirmBankingStatus(this.firmBankingStatus),
        uuid = this.uuid,
    )
}