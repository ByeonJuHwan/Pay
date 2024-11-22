package com.dev.adapter.`in`.web

import com.dev.domain.Hotel
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class RegisterHotelController (

){
    @PostMapping("/hotels")
    fun registerHotel(hotel: Hotel) {}
}