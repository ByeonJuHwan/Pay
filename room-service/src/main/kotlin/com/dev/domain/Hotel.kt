package com.dev.domain

class Hotel private constructor(
    val hotelId: String,
    val hotelName: String,
    val hotelDescription: String,
    val location: String,
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

    companion object {
        fun generateHotel(
            hotelId: HotelId,
            hotelName: HotelName,
            hotelDescription: HotelDescription,
            location: Location,
        ): Hotel {
            return Hotel(
                hotelId.hotelId,
                hotelName.hotelName,
                hotelDescription.hotelDescription,
                location.location,
            )
        }
    }
}