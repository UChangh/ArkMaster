package com.android.arkmaster.main

data class Character(
    var id: Int, // 고유값
    var profileImage: Int, // 캐릭터 이미지
    var korName: String, // 한글 이름
    var engName: String, // 영어 이름
    var type: String, // 계열
    var charComment:String, // 한마디
    var info: String, // 계열 설명
    var weapon: String, // 대표 무기
    var identityName: String, // 아이덴티티
    var identityInfo: String // 아이덴티티 설명
)
