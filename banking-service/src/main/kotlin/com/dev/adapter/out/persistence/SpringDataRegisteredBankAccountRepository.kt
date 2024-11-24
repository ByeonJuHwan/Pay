package com.dev.adapter.out.persistence

import com.dev.adapter.out.persistence.entity.RegisteredBankAccountJpaEntity
import org.springframework.data.jpa.repository.JpaRepository

interface SpringDataRegisteredBankAccountRepository : JpaRepository<RegisteredBankAccountJpaEntity, Long> {
}