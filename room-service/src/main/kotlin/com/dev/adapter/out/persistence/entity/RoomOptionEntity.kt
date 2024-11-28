package com.dev.adapter.out.persistence.entity

import com.dev.adapter.out.persistence.entity.type.RoomType
import com.dev.domain.RoomOption
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "room_options")
class RoomOptionEntity (

    @Enumerated(EnumType.STRING)
    var roomType: RoomType,
    var startDateTime: LocalDateTime,
    var endDateTime: LocalDateTime,
    var price: BigDecimal,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    var room: RoomEntity?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_count_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    var roomCount: RoomCountEntity,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val roomOptionId: Long? = null,
) {
}

fun RoomOptionEntity.toDomain(): RoomOption {
    return RoomOption.generateRoomOption(
        RoomOption.RoomOptionId(this.roomOptionId.toString()),
        RoomOption.StartDate(this.startDateTime.toString()),
        RoomOption.EndDate(this.endDateTime.toString()),
        RoomOption.Amount(this.price.toString()),
        RoomOption.AvailableRoomCount(this.roomCount.toDomain())
    )
}