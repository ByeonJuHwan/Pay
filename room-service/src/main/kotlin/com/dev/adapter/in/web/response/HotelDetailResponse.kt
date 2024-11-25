package com.dev.adapter.`in`.web.response

import com.dev.domain.Hotel

data class HotelDetailResponse(
    val hotelId: String,
    val hotelName: String,
    val hotelDescription: String,
    val location: String,
) {
    companion object {
        fun from(hotel: Hotel): HotelDetailResponse {
            return HotelDetailResponse(
                hotel.hotelId,
                hotel.hotelName,
                hotel.hotelDescription,
                hotel.location,
            )
        }
    }
}