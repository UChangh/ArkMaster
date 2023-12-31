package com.android.arkmaster.user

// 회원 가입 사용자
data class User(
    val name: String,
    val userId: String,
    val userPw: String,
    val nickname: String,
    var representativeCharacter: Int = 0
)


