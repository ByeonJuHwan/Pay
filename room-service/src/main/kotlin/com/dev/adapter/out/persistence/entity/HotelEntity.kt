package com.dev.adapter.out.persistence.entity

import com.dev.domain.Hotel
import jakarta.persistence.*


@Entity
@Table(name = "hotel")
class HotelEntity(
    var hotelName: String,
    var hotelDescription: String,
    var location: String,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val hotelId: Long? = null,
) {
}

fun HotelEntity.toDomain(): Hotel {
    return Hotel.generateHotel(
        Hotel.HotelId(this.hotelId.toString()),
        Hotel.HotelName(this.hotelName),
        Hotel.HotelDescription(this.hotelDescription),
        Hotel.Location(this.location),
    )
}