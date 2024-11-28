package com.dev.application.port.`in`.query

data class FindAvailableHotelDetailQuery(
    val hotelId : Long,
    val startDate: String,
    val endDate: String,
)
