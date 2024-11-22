package com.dev.adapter.`in`.web

import com.dev.common.WebAdapter
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@WebAdapter
@RestController
class FindHotelController (

) {
    @GetMapping("/hotels")
    fun findHotelByHotelId(hotelId: String) : Unit {

    }
}