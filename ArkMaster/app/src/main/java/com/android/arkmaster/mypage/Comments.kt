package com.android.arkmaster.mypage

import com.android.arkmaster.main.Character
import java.time.LocalDate

data class Comments(
    val userEmail: String,  // 유저 검사용
    val characterId: Int,   // 어느 캐릭터에 작성했는지
    val commentText: String,    // 댓글
    val commentTime: LocalDate     // 시간
)
