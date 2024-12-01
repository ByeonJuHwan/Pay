package com.dev.adapter.out.service

// 온전히 이 서비스만을 위한 클래스
data class Membership (
    val membershipId: String,
    val name: String,
    val email: String,
    val address: String,
    val isValid: Boolean,
    val isCorp : Boolean,
)