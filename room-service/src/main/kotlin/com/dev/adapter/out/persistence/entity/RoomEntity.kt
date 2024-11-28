package com.dev.adapter.out.persistence.entity

import com.dev.domain.Room
import jakarta.persistence.*

@Entity
@Table(name = "room")
class RoomEntity (

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id", foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    var hotel: HotelEntity?,
    var roomName: String,
    var roomDescription: String,

    @OneToMany(
        mappedBy = "room",
        cascade = [(CascadeType.PERSIST),(CascadeType.REMOVE)],
        orphanRemoval = true
    )
    private var _roomOptions: MutableSet<RoomOptionEntity> = mutableSetOf(),

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val roomId: Long? = null,
) {

    val roomOptions: Set<RoomOptionEntity>
        get() = _roomOptions.toSet()

    fun addRoomOption(roomOption: RoomOptionEntity) {
        _roomOptions.add(roomOption)
        roomOption.room = this
    }

    fun removeRoomOption(roomOption: RoomOptionEntity) {
        _roomOptions.remove(roomOption)
        roomOption.room = null
    }
}

fun RoomEntity.toDomain(): Room {
    return Room.generateRoom(
        Room.RoomId(this.roomId.toString()),
        Room.RoomName(this.roomName),
        Room.RoomDescription(this.roomDescription),
        Room.RoomOptions(this.roomOptions.map { it.toDomain() }.toSet())
    )
}