package com.android.arkmaster

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout

class SignUpActivity : AnimationActivity(TransitionMode.HORIZON) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        fun isValidPassword(etpassword: String): Boolean {
            val regex = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[^a-zA-Z0-9]).{8,16}$")
            return regex.matches(etpassword)
        }

        val btnSignUp = findViewById<ConstraintLayout>(R.id.signup_button)
        val et_email = findViewById<EditText>(R.id.signup_emailbox)
        //닉네임
        val et_nickname = findViewById<EditText>(R.id.signup_nicknameHint)
        val et_name = findViewById<EditText>(R.id.signup_nameHint)
        val et_password = findViewById<EditText>(R.id.signup_passwordHint)
        val et_password2 = findViewById<EditText>(R.id.signup_checkPasswordHint)
        val tv_errorPW2 = findViewById<TextView>(R.id.signup_errorPW)
        val tv_errorPW = findViewById<TextView>(R.id.signup_EmptyPw)

        val tv_errorName = findViewById<TextView>(R.id.signup_EmptyName)
        val tv_errorNickname = findViewById<TextView>(R.id.Signup_errorNickname)
        val tv_errorEmail = findViewById<TextView>(R.id.signup_erroremail)

        //Spinner 추가//
        val spinner = findViewById<Spinner>(R.id.signup_spinner)
        val item = resources.getStringArray(R.array.spinner_items)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, item)

        spinner.adapter = adapter



        btnSignUp.setOnClickListener() {
            val password1 = et_password.text.toString()
            val password2 = et_password2.text.toString()


            //email 입력 관련..
            //email 빈칸
            if (et_email.text.isBlank()) {
                makeToast(this, "email을 입력해주세요")
                return@setOnClickListener
            }
            //닉네임 입력 관련..
            //닉네임 빈칸
            if (et_nickname.text.isBlank()) {
                makeToast(this, "닉네임을 입력해주세요")
                return@setOnClickListener
            }


            //이름 입력 안했을 시
            if (et_name.text.isBlank()) {
                tv_errorName.visibility = View.VISIBLE
                makeToast(this, "성함을 입력해주세요")
                return@setOnClickListener
            } else {
                tv_errorName.visibility = View.INVISIBLE
                val newName = User(userId = "Default userId", name = et_name.text.toString(), nickname = "Default Name")
                datalist.add(newName)
            }
            //비밀번호
            if (et_password.text.isBlank()) {
                tv_errorPW.visibility = View.VISIBLE
                makeToast(this, "비밀번호를 입력해주세요")
                return@setOnClickListener
            } else if (et_password2.text.isBlank()) {
                makeToast(this, "비밀번호 확인을 해주세요.")
                return@setOnClickListener
            } else if (!isValidPassword(et_password.text.toString())) {
                makeToast(this, "비밀번호 조합이 옳지 않습니다.")
                return@setOnClickListener
            }

            if (password1 != password2) {
                tv_errorPW2.visibility = View.VISIBLE
                makeToast(this, "비밀번호가 다릅니다.")
                return@setOnClickListener
            } else {
                tv_errorPW2.visibility = View.INVISIBLE
            }

            //이메일 중복

            if (isUserEmailUsed(et_email.text.toString())) {
                tv_errorEmail.visibility = View.VISIBLE
                makeToast(this, "이미 사용중인 이메일 입니다.")
                return@setOnClickListener
            } else {
                tv_errorEmail.visibility = View.INVISIBLE
            }


//            닉네임 중복
            if (isNicknameUsed(et_nickname.text.toString())) {
                tv_errorNickname.visibility = View.VISIBLE
                makeToast(this, "이미 사용중인 닉네임 입니다.")
                return@setOnClickListener
            } else {
                tv_errorNickname.visibility = View.INVISIBLE
            }

            val newUser = User(userId = et_email.text.toString(), name = et_name.text.toString(), nickname = et_nickname.text.toString())
            datalist.add(newUser)


            val intent = Intent(this, SignInActivity::class.java).apply {
                putExtra("email", et_email.text.toString())
                putExtra("PW", et_password2.text.toString())
                putExtra("spinner", spinner.selectedItem.toString())
            }
            setResult(RESULT_OK, intent)
            if (!isFinishing) finish()
        }
    }

    fun makeToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun isNicknameUsed(nickname: String): Boolean {
        return datalist.any { it.nickname == nickname }
    }
    //이메일 중복
    fun isUserEmailUsed(userId: String): Boolean {
        return datalist.any { it.userId == userId }
    }

}

//Test: test