package com.dev.adapter.`in`.web

import com.dev.domain.Hotel
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ModifyHotelController {

    @PostMapping("/hotels/{hotelId}")
    fun modifyHotel(hotel: Hotel) {

    }
}