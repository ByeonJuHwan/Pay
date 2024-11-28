package com.dev.application.port.out

import com.dev.adapter.out.persistence.entity.HotelEntity

interface FindAvailableHotelDetailPort {
    fun findAvailableHotelDetail(hotelId: Long, startDate: String, endDate: String): HotelEntity?
}