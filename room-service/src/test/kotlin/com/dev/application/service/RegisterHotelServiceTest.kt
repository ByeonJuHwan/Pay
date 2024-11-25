package com.dev.application.service

import com.dev.adapter.out.persistence.entity.HotelEntity
import com.dev.application.port.`in`.command.RegisterHotelCommand
import com.dev.application.port.out.RegisterHotelPort
import com.dev.domain.Hotel
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class RegisterHotelServiceTest {

  private var registerHotelPort = mockk<RegisterHotelPort>()
  private var registerHotelService: RegisterHotelService = RegisterHotelService(registerHotelPort)

  @Test
  fun `호텔 등록이 성공적으로 완료되어야 한다`() {
    // given
    val command = RegisterHotelCommand(
      "신라호텔",
      "최고의 호텔",
      "경기도 성남시 판교"
    )

    val hotel = HotelEntity(
      command.hotelName,
      command.hotelDescription,
      command.location,
      1L
    )

    every {
      registerHotelPort.registerHotel(
        hotelName = Hotel.HotelName(command.hotelName),
        hotelDescription = Hotel.HotelDescription(command.hotelDescription),
        location = Hotel.Location(command.location)
      )
    } returns hotel

    // when
    val result = registerHotelService.registerHotel(command)

    // then
    verify (exactly = 1){ registerHotelPort.registerHotel(
      Hotel.HotelName(command.hotelName),
      Hotel.HotelDescription(command.hotelDescription),
      Hotel.Location(command.location),
    ) }

    assertThat(result.hotelName).isEqualTo(hotel.hotelName)
  }
}