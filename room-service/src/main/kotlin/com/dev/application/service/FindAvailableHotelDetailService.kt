package com.dev.application.service

import com.dev.adapter.out.persistence.entity.toDomain
import com.dev.application.port.`in`.FindAvailableHotelDetailUseCase
import com.dev.application.port.`in`.query.FindAvailableHotelDetailQuery
import com.dev.application.port.out.FindAvailableHotelDetailPort
import com.dev.domain.Hotel
import org.springframework.stereotype.Service

@Service
class FindAvailableHotelDetailService (
    private val findAvailableHotelDetailPort : FindAvailableHotelDetailPort,
) : FindAvailableHotelDetailUseCase {
    override fun findAvailableHotelDetail(query: FindAvailableHotelDetailQuery) : Hotel {
        // 호텔 디테일 엔티티 검색
        val hotel = findAvailableHotelDetailPort.findAvailableHotelDetail(
            query.hotelId,
            query.startDate,
            query.endDate,
        ) ?: throw RuntimeException("Hotel not found")

        // 도메인으로 변경
        return hotel.toDomain()
    }
}