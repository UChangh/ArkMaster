package com.android.arkmaster

import android.widget.Toast
import com.android.arkmaster.main.Character

// 회원 가입 사용자
data class User(
    val name: String,
    val userId: String,  // userEmail -> userId
    val userPw: String,
    val nickname: String,
    var representativeCharacter: Int? = null    // Character Type -> Int Type, val -> var
)

val datalist = mutableListOf<User>()



