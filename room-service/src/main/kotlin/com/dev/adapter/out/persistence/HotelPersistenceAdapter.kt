package com.dev.adapter.out.persistence

import com.dev.adapter.out.SpringDataHotelRepository
import com.dev.adapter.out.persistence.entity.HotelEntity
import com.dev.application.port.out.RegisterHotelPort
import com.dev.common.PersistenceAdapter
import com.dev.domain.Hotel

@PersistenceAdapter
class HotelPersistenceAdapter (
    private val hotelRepository: SpringDataHotelRepository,
) : RegisterHotelPort {

    override fun registerHotel(
        hotelName: Hotel.HotelName,
        hotelDescription: Hotel.HotelDescription,
        location: Hotel.Location
    ): HotelEntity {
        return hotelRepository.save(
            HotelEntity(
                hotelName.hotelName,
                hotelDescription.hotelDescription,
                location.location
            )
        )
    }
}