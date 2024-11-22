package com.dev.domain

import java.math.BigDecimal

class RoomOption(
    val roomId: String,
    val roomOptionId: String,
    val dateRange: DataRange,
    val roomPrice: Double,
    val amount: BigDecimal,
) {
}