package com.dev.adapter.`in`.web

import com.dev.adapter.out.persistence.SpringDataHotelRepository
import com.dev.adapter.out.persistence.entity.HotelEntity
import com.dev.adapter.out.persistence.entity.RoomCountEntity
import com.dev.adapter.out.persistence.entity.RoomEntity
import com.dev.adapter.out.persistence.entity.RoomOptionEntity
import com.dev.adapter.out.persistence.entity.type.RoomType
import com.dev.adapter.out.persistence.query.SpringDataRoomCountRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import java.time.LocalDateTime

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class FindHotelDetailControllerTest(
    @Autowired private val mockMvc: MockMvc,
    @Autowired private val hotelRepository: SpringDataHotelRepository,
    @Autowired private val roomCountRepository: SpringDataRoomCountRepository,
) {

    private var savedHotelId: Long = 0L

    @BeforeEach
    fun setUp() {
        val testRoomCount = RoomCountEntity(10)
        roomCountRepository.saveAndFlush(testRoomCount)

        val testHotel = HotelEntity(
            hotelName = "신라호텔",
            hotelDescription = "완전 좋아요",
            location = "경기도 성남시"
        )

        val testRoom = RoomEntity(
            hotel = null,
            roomName = "나비방",
            roomDescription = "너무 좋아요"
        )

        val testRoomOption = RoomOptionEntity(
            roomType = RoomType.NOMALROOM,
            startDateTime = LocalDateTime.parse("2024-11-27T00:00:00"),
            endDateTime = LocalDateTime.parse("2024-11-27T23:59:59"),
            price = BigDecimal.TEN,
            room = null,
            roomCount = testRoomCount
        )

        testHotel.addRoom(testRoom)
        testRoom.addRoomOption(testRoomOption)

        savedHotelId = hotelRepository.saveAndFlush(testHotel).hotelId!!
    }

    @Test
    fun `호텔 Id, 예약날짜를 입력하면 해당 날짜의 호텔의 상세 정보가 조회된다`() {
        mockMvc.get("/hotels/$savedHotelId") {
            param("startDate", "20241127")
            param("endDate", "20241127")
        }.andExpect {
            status { isOk() }
            jsonPath("$.hotelName") { value("신라호텔") }
        }.andDo { print() }
    }
}