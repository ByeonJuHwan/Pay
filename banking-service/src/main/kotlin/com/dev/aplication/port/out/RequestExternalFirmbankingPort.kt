package com.dev.aplication.port.out

import com.dev.adapter.out.external.bank.ExternalFirmbankingRequest
import com.dev.adapter.out.external.bank.FirmbankingResult

interface RequestExternalFirmbankingPort {
    fun requestExternalFirmbanking(request: ExternalFirmbankingRequest) : FirmbankingResult
}