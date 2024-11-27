package com.dev.adapter.out.persistence.query

import com.dev.adapter.out.persistence.entity.HotelEntity
import com.dev.domain.Hotel


interface SpringDataHotelQueryRepository {
    fun addAvailableRooms(location: String?, startDate: String?, endDate: String?, lastId: Long?, size: Long): List<Hotel>
}