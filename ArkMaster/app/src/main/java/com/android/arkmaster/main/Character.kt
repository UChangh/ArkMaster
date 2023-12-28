package com.android.arkmaster.main

data class Character(
    val id: Int, // 고유값
    val profileImage: Int, // 캐릭터 이미지
    val korName: String, // 한글 이름
    val engName: String, // 영어 이름
    val type: String, // 계열
    val char_comment:String, // 한마디
    val info: String, // 계열 설명
    val weapon: String, // 대표 무기
    val idenName: String, // 아이덴티티
    val ideninfo: String // 아이덴티티 설명
)
