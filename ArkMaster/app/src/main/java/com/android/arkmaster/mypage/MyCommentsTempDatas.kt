package com.android.arkmaster.mypage

import com.android.arkmaster.main.CharacterManager
import java.time.LocalDate

class MyCommentsTempDatas {
    val dataset = mutableListOf(
        Comments(
            "유저 이메일 1",
            "소서리스",
            "소서리스 캐릭터 플레이 팁 공유드립니다.",
            "${LocalDate.now()}"
        ), Comments(
            "유저 이메일 2",
            "디스트로이어",
            "디스트로이어 플레이 팁 공유해주세요 ~ ",
            "${LocalDate.now()}"
        ), Comments(
            "유저 이메일 3",
            "버서커",
            "버서커는 좀 어렵네요 .. ",
            "${LocalDate.now()}"
        )
    )


    // comments size
    fun getCommentSize(name: String): Int = dataset.count { it.charName == name }

    fun getComment(korName: String): List<Comments>{
        return dataset
    }

}