package com.android.arkmaster

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.constraintlayout.widget.ConstraintLayout

class SignUpActivity : AnimationActivity(TransitionMode.HORIZON) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        fun isValidPassword(etPwd: String): Boolean {
            val regex = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[^a-zA-Z0-9]).{8,16}$")
            return regex.matches(etPwd)
        }

        val btnSignUp = findViewById<ConstraintLayout>(R.id.signup_button)
        val etEmail = findViewById<EditText>(R.id.signup_emailbox)
        //닉네임
        val etNickName = findViewById<EditText>(R.id.signup_nicknameHint)
        val etName = findViewById<EditText>(R.id.signup_nameHint)
        val etPwd = findViewById<EditText>(R.id.signup_passwordHint)
        val etPwd2 = findViewById<EditText>(R.id.signup_checkPasswordHint)
        val tvErrorPwd2 = findViewById<TextView>(R.id.signup_errorPW)
        val tvErrorPwd = findViewById<TextView>(R.id.signup_EmptyPw)

        val tvErrorName = findViewById<TextView>(R.id.signup_EmptyName)
        val tvErrorNickName = findViewById<TextView>(R.id.Signup_errorNickname)
        val tvErrorEmail = findViewById<TextView>(R.id.signup_erroremail)

        // Spinner 추가
        val spinner = findViewById<Spinner>(R.id.signup_spinner)
        val item = resources.getStringArray(R.array.spinner_items)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, item)

        spinner.adapter = adapter

        btnSignUp.setOnClickListener() {
            val password1 = etPwd.text.toString()
            val password2 = etPwd2.text.toString()

            //email 빈칸
            if (etEmail.text.isBlank()) {
                makeToast(this, getString(R.string.signup_emailHint))
                return@setOnClickListener
            }

            //닉네임 빈칸
            if (etNickName.text.isBlank()) {
                makeToast(this, getString(R.string.signup_nicnameHint))
                return@setOnClickListener
            }

            //이름 입력 안했을 시
            if (etName.text.isBlank()) {
                tvErrorName.visibility = View.VISIBLE
                makeToast(this, getString(R.string.signup_nameHint))
                return@setOnClickListener
            } else {
                tvErrorName.visibility = View.INVISIBLE
            }
            //비밀 번호
            if (etPwd.text.isBlank()) {
                tvErrorPwd.visibility = View.VISIBLE
                makeToast(this, getString(R.string.signup_passwordHint))
                return@setOnClickListener
            } else if (etPwd2.text.isBlank()) {
                makeToast(this, getString(R.string.signup_passwordHint))
                return@setOnClickListener
            } else if (!isValidPassword(etPwd.text.toString())) {
                makeToast(this, getString(R.string.signup_pwlimit))
                return@setOnClickListener
            }

            if (password1 != password2) {
                tvErrorPwd2.visibility = View.VISIBLE
                makeToast(this, getString(R.string.signup_errorcheckpassword))
                return@setOnClickListener
            } else {
                tvErrorPwd2.visibility = View.INVISIBLE
            }

            // 이메일 중복
            if (isUserEmailUsed(etEmail.text.toString())) {
                tvErrorEmail.visibility = View.VISIBLE
                makeToast(this, getString(R.string.signup_erroremail))
                return@setOnClickListener
            } else {
                tvErrorEmail.visibility = View.INVISIBLE
            }

            // 닉네임 중복
            if (isNickNameUsed(etNickName.text.toString())) {
                tvErrorNickName.visibility = View.VISIBLE
                makeToast(this, getString(R.string.signup_errornickname))
                return@setOnClickListener
            } else {
                tvErrorNickName.visibility = View.INVISIBLE
            }

            val newUser = User(userId = etEmail.text.toString() + spinner.selectedItem, userPw = password1, name = etName.text.toString(), nickname = etNickName.text.toString())
            datalist.add(newUser)

            val intent = Intent(this, SignInActivity::class.java).apply {
                putExtra("email", etEmail.text.toString() + spinner.selectedItem)
                putExtra("PW", etPwd2.text.toString())
            }
            setResult(RESULT_OK, intent)
            if (!isFinishing) finish()
        }
    }

    private fun makeToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private fun isNickNameUsed(nickname: String): Boolean {
        return datalist.any { it.nickname == nickname }
    }
    //이메일 중복
    private fun isUserEmailUsed(userId: String): Boolean {
        return datalist.any { it.userId == userId }
    }

    private val selectItemLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val selectedValue = result.data?.getStringExtra("selectedValue")
        }
    }
}