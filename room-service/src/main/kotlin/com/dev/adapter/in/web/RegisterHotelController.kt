package com.dev.adapter.`in`.web

import com.dev.adapter.`in`.web.request.RegisterHotelRequest
import com.dev.adapter.`in`.web.response.HotelDetailResponse
import com.dev.application.port.`in`.RegisterHotelUseCase
import com.dev.common.WebAdapter
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@WebAdapter
@RestController
class RegisterHotelController(
    private val hotelRegisterUserCase: RegisterHotelUseCase
){
    @PostMapping("/hotels")
    fun registerHotel(@RequestBody request: RegisterHotelRequest): HotelDetailResponse {
        val command = request.toCommand()
        val hotel = hotelRegisterUserCase.registerHotel(command)
        return HotelDetailResponse.from(hotel)
    }
}