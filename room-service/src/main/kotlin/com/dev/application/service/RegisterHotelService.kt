package com.dev.application.service

import com.dev.adapter.out.persistence.entity.toDomain
import com.dev.application.port.`in`.RegisterHotelUseCase
import com.dev.application.port.`in`.command.RegisterHotelCommand
import com.dev.application.port.out.RegisterHotelPort
import com.dev.domain.Hotel
import org.springframework.stereotype.Service

@Service
class RegisterHotelService(
    private val registerHotelPort: RegisterHotelPort,
) : RegisterHotelUseCase {

    override fun registerHotel(command: RegisterHotelCommand) : Hotel {
        return registerHotelPort.registerHotel(
            Hotel.HotelName(command.hotelName),
            Hotel.HotelDescription(command.hotelDescription),
            Hotel.Location(command.location),
        ).toDomain()
    }
}