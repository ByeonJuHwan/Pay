package com.dev.application.port.`in`.query

data class FindAvailableRoomQuery(
    val location: String?,
    val startDate: String?,
    val endDate: String?,
)