package com.android.arkmaster.user

object UserManager {
    val userList = mutableListOf<User>()
    fun getUserNickname(email: String): String {
        return userList.find { it.userId == email }?.nickname ?: ""
    }

    fun getUserCharacter(email: String): Int {
        return userList.find { it.userId == email }?.representativeCharacter ?: 0
    }
}