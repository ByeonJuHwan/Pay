package com.dev.adapter.out.persistence

import com.dev.adapter.out.persistence.entity.FirmbankingJpaEntity
import org.springframework.data.jpa.repository.JpaRepository

interface SpringDataFirmbankingRequestRepository : JpaRepository<FirmbankingJpaEntity, Long> {
}