package com.dev.domain

class HotelSearchPage<T> private constructor(
    val content: List<T>,
    val hasNext: Boolean,
    val lastId: String?,
) {
    companion object {
        fun <T> create (
            content: List<T>,
            hasNext: Boolean,
            lastId: String?,
        ): HotelSearchPage<T> {
            return HotelSearchPage(
                content = content,
                hasNext = hasNext,
                lastId = lastId,
            )
        }
    }
}