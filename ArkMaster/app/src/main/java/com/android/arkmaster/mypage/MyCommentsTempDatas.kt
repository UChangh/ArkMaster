package com.android.arkmaster.mypage

import com.android.arkmaster.main.CharacterManager
import java.time.LocalDate

class MyCommentsTempDatas {
    val dataset = mutableListOf(
        Comments(
            "유저 이메일 1",
            "소서리스",
            "소서리스 캐릭터 플레이 팁 공유드립니다.",
            "${LocalDate.of(2023,12,1)}"
        ), Comments(
            "유저 이메일 2",
            "디스트로이어",
            "디스트로이어 플레이 팁 공유해주세요 ~ ",
            "${LocalDate.of(2023,12,4)}"
        ), Comments(
            "유저 이메일 4",
            "버서커",
            "버서커는 좀 어렵네요 .. ",
            "${LocalDate.of(2023,12,4)}"
        ), Comments(
            "유저 이메일 5",
            "버서커",
            "버서커는 좀 어렵네요 .. ",
            "${LocalDate.of(2023,12,5)}"
        ), Comments(
            "유저 이메일 6",
            "버서커",
            "버서커는 좀 어렵네요 .. ",
            "${LocalDate.of(2023,12,7)}"
        ), Comments(
            "유저 이메일 7",
            "버서커",
            "버서커는 좀 어렵네요 .. ",
            "${LocalDate.of(2023,12,9)}"
        ), Comments(
            "유저 이메일 8",
            "버서커",
            "버서커는 좀 어렵네요 .. ",
            "${LocalDate.of(2023,12,14)}"
        ), Comments(
            "유저 이메일 9",
            "버서커",
            "버서커는 좀 어렵네요 .. ",
            "${LocalDate.of(2023,12,16)}"
        ), Comments(
            "유저 이메일 10",
            "버서커",
            "버서커는 좀 어렵네요 .. ",
            "${LocalDate.of(2023,12,23)}"
        ), Comments(
            "유저 이메일 3",
            "소서리스",
            "소서리스는 좀 괜찮네요 .. ",
            "${LocalDate.now()}"
        )
    )


    // comments size
    fun getCommentSize(name: String): Int = dataset.count { it.charName == name }

    fun getComment(korName: String): List<Comments>{
        return dataset
    }

}