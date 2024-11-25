package com.dev.application.port.`in`.command

data class RegisterHotelCommand(
    val hotelName: String,
    val hotelDescription: String,
    val location: String,
)