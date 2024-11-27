package com.dev.adapter.`in`.web.response

import com.dev.domain.Hotel

data class FindAvailableHotelResponse(
    val data: List<Hotel>,
    val haxNext: Boolean,
    val lastId: String?,
)
