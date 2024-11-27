package com.dev.application.port.`in`

import com.dev.application.port.`in`.query.FindAvailableRoomQuery
import com.dev.domain.Hotel
import com.dev.domain.HotelSearchPage
import com.dev.domain.Room
import org.springframework.data.domain.Page

interface FindAvailableRoomUseCase {

    fun findAvailableRoom(query: FindAvailableRoomQuery, lastId: Long?, size: Long): HotelSearchPage<Hotel>
}