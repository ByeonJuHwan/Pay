package com.dev.adapter.out.persistence

import com.dev.adapter.out.persistence.entity.HotelEntity
import com.dev.adapter.out.persistence.query.SpringDataHotelQueryRepository
import org.springframework.data.jpa.repository.JpaRepository

interface SpringDataHotelRepository : JpaRepository<HotelEntity, Long>, SpringDataHotelQueryRepository {
}