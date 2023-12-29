package com.android.arkmaster.mypage

import android.provider.ContactsContract.CommonDataKinds.Nickname
import com.android.arkmaster.User

class SigninUser(private var name: String,private var nickname: String, private var email: String, private var password: String): UserData(name, nickname, email, password) {
    override val username:String = name
    override val usernickname:String = nickname
    override val useremail:String = email
    override val userpw:String = password
}

abstract class UserData(name:String,nickname: String, email:String, password:String) {
    abstract val username:String
    abstract val useremail:String
    abstract val usernickname:String
    abstract val userpw:String
}

class DataList {
    companion object {
        var datalist = mutableListOf<SigninUser>()
    }
}