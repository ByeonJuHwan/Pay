package com.dev.application.port.`in`

import com.dev.application.port.`in`.query.FindAvailableHotelDetailQuery
import com.dev.domain.Hotel

interface FindAvailableHotelDetailUseCase {
    fun findAvailableHotelDetail(query: FindAvailableHotelDetailQuery): Hotel
}