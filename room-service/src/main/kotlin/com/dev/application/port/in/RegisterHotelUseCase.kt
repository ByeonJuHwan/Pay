package com.dev.application.port.`in`

import com.dev.application.port.`in`.command.RegisterHotelCommand
import com.dev.domain.Hotel

interface RegisterHotelUseCase {
    fun registerHotel(command: RegisterHotelCommand) : Hotel
}