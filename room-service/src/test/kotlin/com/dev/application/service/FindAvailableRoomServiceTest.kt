package com.dev.application.service

import com.dev.application.port.`in`.query.FindAvailableRoomQuery
import com.dev.application.port.out.FindAvailableRoomPort
import com.dev.domain.Hotel
import com.dev.domain.Room
import com.dev.domain.RoomCount
import com.dev.domain.RoomOption
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class FindAvailableRoomServiceTest {

    private var findHotelPort = mockk<FindAvailableRoomPort>()
    private var findAvailableRoomService: FindAvailableRoomService = FindAvailableRoomService(findHotelPort)

    @Test
    fun `지역, 날짜, 페이징 검색조건을 입력하면 예약가능한 호텔 목록이 조회된다`() {
        // given
        val query = FindAvailableRoomQuery(
            "경기도 성남시",
            "20241117",
            "20241118",
        )

        val mockHotels = listOf(
            createTestHotel("H1", "호텔1"),
            createTestHotel("H2", "호텔2"),
            createTestHotel("H3", "호텔3")
        )

        every {
            findHotelPort.findAvailableRooms(
                query.location,
                query.startDate,
                query.endDate,
                null,
                2
            )
        } returns mockHotels

        // when
        val result = findAvailableRoomService.findAvailableRoom(query, null, 2)

        // then
        assertThat(result.content).hasSize(2)
        assertThat(result.content.first().rooms.first().roomName).isEqualTo("좋은방")
        assertThat(result.hasNext).isTrue()
        assertThat(result.lastId).isEqualTo("H2")

    }

    private fun createTestHotel(hotelId: String, name: String) = Hotel.generateHotel(
        Hotel.HotelId(hotelId),
        Hotel.HotelName(name),
        Hotel.HotelDescription("몽글몽글"),
        Hotel.Location("경기도 성남시"),
        Hotel.Rooms(setOf(createTestRoom("R1")))
    )

    private fun createTestRoom(hotelId: String) = Room.generateRoom(
        Room.RoomId("R1"),
        Room.RoomName("좋은방"),
        Room.RoomDescription("좋은방 어서와요"),
        Room.RoomOptions(setOf(createTestRoomOption("O1")))
    )

    private fun createTestRoomOption(roomId: String) = RoomOption.generateRoomOption(
        RoomOption.RoomOptionId("O1"),
        RoomOption.StartDate("20241117"),
        RoomOption.EndDate("20241118"),
        RoomOption.Amount("100"),
        RoomOption.AvailableRoomCount(crateTestRoomCount())
    )

    private fun crateTestRoomCount() = RoomCount.generateRoomCount(
        RoomCount.RoomCountId("RC1"),
        RoomCount.AvailableRooms(10)
    )
}