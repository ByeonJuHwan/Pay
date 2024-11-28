package com.dev.adapter.`in`.web

import com.dev.application.port.`in`.FindAvailableHotelDetailUseCase
import com.dev.application.port.`in`.query.FindAvailableHotelDetailQuery
import com.dev.domain.Hotel
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class FindHotelDetailController(
    private val findAvailableHotelDetailUseCase: FindAvailableHotelDetailUseCase,
) {

    @GetMapping("/hotels/{hotelId}")
    fun searchAvailableHotelDetail(
        @PathVariable hotelId: Long,
        @RequestParam startDate: String, // 숙박 시작 일자
        @RequestParam endDate: String, // 숙박 종료 일자
    ): ResponseEntity<Hotel> {
        val query = FindAvailableHotelDetailQuery(hotelId, startDate, endDate)
        val hotel = findAvailableHotelDetailUseCase.findAvailableHotelDetail(query)
        return ResponseEntity.ok(hotel)
    }
}