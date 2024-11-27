package com.dev.adapter.out.persistence.query

import com.dev.adapter.out.persistence.entity.QHotelEntity.hotelEntity
import com.dev.adapter.out.persistence.entity.QRoomEntity.roomEntity
import com.dev.adapter.out.persistence.entity.QRoomOptionEntity.roomOptionEntity
import com.dev.adapter.out.persistence.entity.toDomain
import com.dev.domain.Hotel
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class SpringDataHotelQueryRepositoryImpl(
    private val queryFactory: JPAQueryFactory,
) : SpringDataHotelQueryRepository {

    override fun addAvailableRooms(location: String?, startDate: String?, endDate: String?, lastId: Long?, size: Long) : List<Hotel> {
        return queryFactory.select(hotelEntity)
            .from(hotelEntity)
            .join(hotelEntity._rooms, roomEntity).fetchJoin()
            .join(roomEntity._roomOptions, roomOptionEntity).fetchJoin()
            .where(
                searchLocation(location),
                checkPeriod(startDate, endDate),
                getLastId(lastId),
            )
            .limit(size + 1)
            .fetch()
            .map { it.toDomain() }
    }

    private fun searchLocation(location: String?): BooleanExpression? {
        return location?.let { hotelEntity.location.contains(it) }
    }
    private fun checkPeriod(startDate: String?, endDate: String?): BooleanExpression? {
        if(startDate == null || endDate == null) return null

        val start = LocalDateTime.parse(startDate + "000000",DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))
        val end = LocalDateTime.parse(endDate + "235959", DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))

        return roomOptionEntity.startDateTime.goe(start)
            .and(roomOptionEntity.endDateTime.loe(end))
    }
    private fun getLastId(lastId: Long?): BooleanExpression? {
        return lastId?.let { hotelEntity.hotelId.gt(lastId) }
    }
}