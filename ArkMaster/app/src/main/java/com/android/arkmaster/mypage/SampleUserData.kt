package com.android.arkmaster.mypage

import com.android.arkmaster.User
import com.android.arkmaster.datalist

class SampleUserData {
    fun sampleUserData() {  // User타입의 샘플 데이터 생성
        if(datalist.isEmpty()) {
            datalist.add(
                User(
                    "Administrator",
                    "root",
                    "1234",
                    "Admin"
                )
            )
        }
    }
}