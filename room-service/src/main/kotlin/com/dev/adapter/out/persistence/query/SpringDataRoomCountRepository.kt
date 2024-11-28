package com.dev.adapter.out.persistence.query

import com.dev.adapter.out.persistence.entity.RoomCountEntity
import org.springframework.data.jpa.repository.JpaRepository

interface SpringDataRoomCountRepository : JpaRepository<RoomCountEntity, Long> {
}