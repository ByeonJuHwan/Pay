package com.dev.domain

class Hotel private constructor(
    val hotelId: String,
    val hotelName: String,
    val hotelDescription: String,
    val location: String,
    val rooms: Set<Room>
) {
    data class HotelId(
        val hotelId: String
    )
    data class HotelName(
        val hotelName: String
    )
    data class HotelDescription(
        val hotelDescription: String
    )
    data class Location(
        val location: String
    )
    data class Rooms(
        val rooms: Set<Room>
    )

    companion object {
        fun generateHotel(
            hotelId: HotelId,
            hotelName: HotelName,
            hotelDescription: HotelDescription,
            location: Location,
            rooms: Rooms,
        ): Hotel {
            return Hotel(
                hotelId.hotelId,
                hotelName.hotelName,
                hotelDescription.hotelDescription,
                location.location,
                rooms.rooms,
            )
        }
    }
}