package com.dev.application.port.out

import com.dev.adapter.out.external.bank.ExternalFirmbankingRequest
import com.dev.adapter.out.external.bank.FirmbankingResult

interface RequestExternalFirmbankingPort {
    fun requestExternalFirmbanking(request: ExternalFirmbankingRequest) : FirmbankingResult
}