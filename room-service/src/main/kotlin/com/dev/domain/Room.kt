package com.dev.domain

class Room private constructor(
    val hotelId: String,
    val roomId: String,
    val roomName: String,
    val roomDescription: String,
    val roomOptions: Set<RoomOption>,
) {
    data class HotelId(
        val hotelId: String,
    )
    data class RoomId(
        val roomId: String,
    )
    data class RoomName(
        val roomName: String,
    )
    data class RoomDescription(
        val roomDescription: String,
    )
    data class RoomOptions(
        val roomOptions: Set<RoomOption>,
    )

    companion object {
        fun generateRoom(
            hotelId: HotelId,
            roomId: RoomId,
            roomName: RoomName,
            roomDescription: RoomDescription,
            roomOptions: RoomOptions,
        ): Room {
            return Room(
                hotelId.hotelId,
                roomId.roomId,
                roomName.roomName,
                roomDescription.roomDescription,
                roomOptions.roomOptions,
            )
        }
    }
}