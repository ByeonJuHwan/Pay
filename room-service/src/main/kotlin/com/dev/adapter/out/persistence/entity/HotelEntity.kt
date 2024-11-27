package com.dev.adapter.out.persistence.entity

import com.dev.domain.Hotel
import jakarta.persistence.*


@Entity
@Table(name = "hotel")
class HotelEntity(
    var hotelName: String,
    var hotelDescription: String,
    var location: String,

    @OneToMany(
        mappedBy = "hotel",
        cascade = [(CascadeType.PERSIST),(CascadeType.REMOVE)],
        orphanRemoval = true
    )
    private var _rooms: MutableSet<RoomEntity> = mutableSetOf(),

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val hotelId: Long? = null,
) {
    val rooms: Set<RoomEntity>
        get() = _rooms.toSet()

    fun addRoom(room: RoomEntity) {
        _rooms.add(room)
        room.hotel = this
    }

    fun removeRoom(room: RoomEntity) {
        _rooms.remove(room)
        room.hotel = null
    }
}

fun HotelEntity.toDomain(): Hotel {
    return Hotel.generateHotel(
        Hotel.HotelId(this.hotelId.toString()),
        Hotel.HotelName(this.hotelName),
        Hotel.HotelDescription(this.hotelDescription),
        Hotel.Location(this.location),
        Hotel.Rooms(this.rooms.map { it.toDomain() }.toSet())
    )
}