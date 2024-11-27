package com.dev.application.port.out

import com.dev.adapter.out.persistence.entity.HotelEntity
import com.dev.domain.Hotel

interface FindAvailableRoomPort {
    fun findAvailableRooms(location: String?, startDate: String?, endDate: String?, lastId: Long?, size: Long): List<Hotel>
}