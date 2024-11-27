package com.dev.adapter.`in`.web.response

import com.dev.domain.Hotel
import com.dev.domain.HotelSearchPage

data class FindAvailableHotelResponse(
    val data: List<Hotel>,
    val haxNext: Boolean,
    val lastId: String?,
) {
    companion object {
        fun from(response: HotelSearchPage<Hotel>): FindAvailableHotelResponse {
            return FindAvailableHotelResponse(
                response.content,
                response.hasNext,
                response.lastId,
            )
        }
    }
}
