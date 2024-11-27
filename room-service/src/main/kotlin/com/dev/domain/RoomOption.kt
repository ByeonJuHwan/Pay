package com.dev.domain

import java.time.LocalDate

class RoomOption private constructor(
    val roomId: String,
    val roomOptionId: String,
    val startDate: String,
    val endDate: String,
    val amount: String,
) {
    data class RoomId(
        val roomId: String,
    )
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

    companion object {
        fun generateRoomOption(
            roomId: RoomId,
            roomOptionId: RoomOptionId,
            startDate: StartDate,
            endDate: EndDate,
            amount: Amount,
        ): RoomOption {
            return RoomOption(
                roomId.roomId,
                roomOptionId.roomOptionId,
                startDate.startDate,
                endDate.endDate,
                amount.amount,
            )
        }
    }
}