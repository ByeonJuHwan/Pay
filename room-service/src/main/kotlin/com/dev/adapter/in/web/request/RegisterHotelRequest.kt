package com.dev.adapter.`in`.web.request

import com.dev.application.port.`in`.command.RegisterHotelCommand

data class RegisterHotelRequest(
    val hotelName: String,
    val hotelDescription: String,
    val location: String,
) {
    fun toCommand(): RegisterHotelCommand {
        return RegisterHotelCommand(
            hotelName,
            hotelDescription,
            location
        )
    }
}