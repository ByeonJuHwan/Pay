package com.dev.adapter.out.persistence.entity

import com.dev.domain.RoomCount
import jakarta.persistence.*

@Entity
@Table(name = "room_count")
class RoomCountEntity (

    var roomCount: Int,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val roomCountId: Long? = null,
) {

}

fun RoomCountEntity.toDomain() : RoomCount{
    return RoomCount.generateRoomCount(
        RoomCount.RoomCountId(this.roomCountId.toString()),
        RoomCount.AvailableRooms(this.roomCount)
    )
}