package com.dev.application.port.out

import com.dev.adapter.out.persistence.entity.HotelEntity
import com.dev.domain.Hotel

interface RegisterHotelPort {
    fun registerHotel(
        hotelName: Hotel.HotelName,
        hotelDescription: Hotel.HotelDescription,
        location: Hotel.Location,
    ) : HotelEntity
}