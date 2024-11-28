package com.dev.domain

class RoomOption private constructor(
    val roomOptionId: String,
    val startDate: String,
    val endDate: String,
    val amount: String,
    val roomCount: RoomCount,
) {
    data class RoomOptionId(
        val roomOptionId: String,
    )
    data class StartDate(
        val startDate: String,
    )
    data class EndDate(
        val endDate: String,
    )
    data class Amount(
        val amount: String,
    )
    data class AvailableRoomCount(
        val roomCount: RoomCount,
    )

    companion object {
        fun generateRoomOption(
            roomOptionId: RoomOptionId,
            startDate: StartDate,
            endDate: EndDate,
            amount: Amount,
            roomCount: AvailableRoomCount,
        ): RoomOption {
            return RoomOption(
                roomOptionId.roomOptionId,
                startDate.startDate,
                endDate.endDate,
                amount.amount,
                roomCount.roomCount,
            )
        }
    }
}