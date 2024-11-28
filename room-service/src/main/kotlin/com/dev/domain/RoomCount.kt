package com.dev.domain

class RoomCount private constructor(
    val roomCountId: String,
    val availableRooms: Int,
) {
    data class RoomCountId(
        val roomCountId: String,
    )
    data class AvailableRooms(
        val availableRooms: Int,
    )

    companion object {
        fun generateRoomCount(
            roomCountId: RoomCountId,
            availableRooms: AvailableRooms,
        ) : RoomCount {
            return RoomCount(
                availableRooms = availableRooms.availableRooms,
                roomCountId = roomCountId.roomCountId
            )
        }
    }
}