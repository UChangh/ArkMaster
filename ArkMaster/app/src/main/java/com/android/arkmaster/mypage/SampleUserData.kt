package com.android.arkmaster.mypage

import com.android.arkmaster.R
import com.android.arkmaster.user.User
import com.android.arkmaster.user.userList

class SampleUserData {
    fun sampleUserData() {  // User타입의 샘플 데이터 생성
        if (userList.isEmpty()) {
            userList.add(
                User(
                    "Administrator",
                    "root",
                    "1234",
                    "Admin",
                    6
                )
            )
        }
    }
}