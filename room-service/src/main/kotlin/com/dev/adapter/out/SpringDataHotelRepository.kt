package com.dev.adapter.out

import com.dev.adapter.out.persistence.entity.HotelEntity
import org.springframework.data.jpa.repository.JpaRepository

interface SpringDataHotelRepository : JpaRepository<HotelEntity, Long> {
}