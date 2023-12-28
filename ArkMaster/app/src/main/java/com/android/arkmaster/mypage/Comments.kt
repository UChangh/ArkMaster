package com.android.arkmaster.mypage

data class Comments(
    val userEmail: String,  // 유저 검사용
    val charName: String,   // 어느 캐릭터에 작성했는지
    val commentText: String,    // 댓글
    val commentTime: String     // 시간
)
