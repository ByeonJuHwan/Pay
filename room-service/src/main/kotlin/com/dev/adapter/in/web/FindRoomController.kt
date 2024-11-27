package com.dev.adapter.`in`.web

import com.dev.adapter.`in`.web.response.FindAvailableHotelResponse
import com.dev.application.port.`in`.FindAvailableRoomUseCase
import com.dev.application.port.`in`.query.FindAvailableRoomQuery
import com.dev.common.WebAdapter
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@WebAdapter
@RestController
class FindRoomController (
    private val findAvailableRoomUseCase: FindAvailableRoomUseCase,
) {
    @GetMapping("/hotels")
    fun searchAvailableRoom (
        @RequestParam(required = false) location: String, // 지역검색
        @RequestParam(required = false) startDate: String, // 숙박 시작 일자
        @RequestParam(required = false) endDate: String, // 숙박 종료 일자
        @RequestParam(required = false) lastId: Long?,
        @RequestParam(defaultValue = "10") size: Long,
        pageable: Pageable,
    ) : FindAvailableHotelResponse {
        val query = FindAvailableRoomQuery(location, startDate, endDate)
        val availableRooms = findAvailableRoomUseCase.findAvailableRoom(query, lastId, size)
        return FindAvailableHotelResponse(
            availableRooms.content,
            availableRooms.hasNext,
            availableRooms.lastId,
        )
    }
}