package com.dev.adapter.out.persistence.entity

import jakarta.persistence.*

@Entity
@Table(name = "room_count")
class RoomCountEntity (

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    var room: RoomEntity,
    var roomCount: Int,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val roomCountId: Long? = null,
) {

}