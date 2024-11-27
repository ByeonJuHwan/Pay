package com.dev.application.service

import com.dev.application.port.`in`.FindAvailableRoomUseCase
import com.dev.application.port.`in`.query.FindAvailableRoomQuery
import com.dev.application.port.out.FindAvailableRoomPort
import com.dev.domain.Hotel
import com.dev.domain.HotelSearchPage
import org.springframework.stereotype.Service

@Service
class FindAvailableRoomService (
    private val hotelRoomPort: FindAvailableRoomPort
) : FindAvailableRoomUseCase {

    override fun findAvailableRoom(query: FindAvailableRoomQuery, lastId: Long?, size: Long): HotelSearchPage<Hotel> {
        val findAvailableRoomsPlusOne = hotelRoomPort.findAvailableRooms(
            query.location,
            query.startDate,
            query.endDate,
            lastId,
            size
        )

        val hasNext = findAvailableRoomsPlusOne.size > size

        val hotels = if(hasNext) {
            findAvailableRoomsPlusOne.subList(0,size.toInt())
        } else {
            findAvailableRoomsPlusOne
        }

        val currentLastId = hotels.lastOrNull()?.hotelId


        return HotelSearchPage.create(
            hotels,
            hasNext,
            currentLastId
        )
    }
}