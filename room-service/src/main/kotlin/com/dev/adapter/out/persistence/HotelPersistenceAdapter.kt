package com.dev.adapter.out.persistence

import com.dev.adapter.out.persistence.entity.HotelEntity
import com.dev.application.port.out.FindAvailableRoomPort
import com.dev.application.port.out.RegisterHotelPort
import com.dev.common.PersistenceAdapter
import com.dev.domain.Hotel

@PersistenceAdapter
class HotelPersistenceAdapter (
    private val hotelRepository: SpringDataHotelRepository,
) : RegisterHotelPort, FindAvailableRoomPort {

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

    override fun findAvailableRooms(location: String?, startDate: String?, endDate: String?, lastId: Long?, size: Long): List<Hotel> {
        return hotelRepository.addAvailableRooms(location, startDate, endDate, lastId, size)
    }
}