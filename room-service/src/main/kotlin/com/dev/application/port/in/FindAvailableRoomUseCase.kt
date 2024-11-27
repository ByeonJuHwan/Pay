package com.dev.application.port.`in`

import com.dev.application.port.`in`.query.FindAvailableRoomQuery
import com.dev.domain.Hotel
import com.dev.domain.HotelSearchPage

interface FindAvailableRoomUseCase {

    fun findAvailableRoom(query: FindAvailableRoomQuery, lastId: Long?, size: Long): HotelSearchPage<Hotel>
}