package com.android.arkmaster.mypage

import java.time.LocalDate

object MyCommentsTempDatas {
    val dataset = mutableListOf(
        Comments(
            "root",
            1,
            "소서리스 캐릭터 플레이 팁 공유드립니다.",
            LocalDate.of(2023, 12, 1)
        ), Comments(
            "root",
            0,
            "브레이커 플레이 팁 공유해주세요 ~ ",
            LocalDate.of(2023, 12, 4)
        ), Comments(
            "root",
            0,
            "브레이커 좀 어렵네요 .. ",
            LocalDate.of(2023, 12, 4)
        ), Comments(
            "root",
            1,
            "소서리스 좀 어렵네요 .. ",
            LocalDate.of(2023, 12, 9)
        ), Comments(
            "root",
            2,
            "스트라이커 좀 어렵네요 .. ",
            LocalDate.of(2023, 12, 16)
        ), Comments(
            "root",
            1,
            "소서리스 좀 괜찮네요 .. ",
            LocalDate.now()
        )
    )

    // comments size
    fun getCommentSize(characterId: Int): Int = dataset.count { it.characterId == characterId }

    fun getCharacterComment(id: Int): List<Comments> {
        return dataset.filter { it.characterId == id }
    }

    fun getCharacterComment(email: String): MutableList<Comments> {
        return dataset.filter { it.userEmail == email }.toMutableList()
    }

    fun addComment(comment: Comments) {
        dataset.add(comment)
    }

}