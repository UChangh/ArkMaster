package com.android.arkmaster

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.AppCompatButton
import com.android.arkmaster.main.MainActivity

class SignInActivity : AnimationActivity(TransitionMode.HORIZON) {

    companion object {
        var currentUserId: String? = null
    }

    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        // startActivity(Intent(this, MainActivity::class.java))

        //회원 가입 화면 에서 ID, PW, Spinner 가지고 오기.
        val etId = findViewById<EditText>(R.id.signin_etid)
        val etPw = findViewById<EditText>(R.id.signin_etpw)

        activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == RESULT_OK) {
                    val receiveID = it.data?.getStringExtra("email").orEmpty()
                    val receivePW = it.data?.getStringExtra("PW").orEmpty()

                    etId.setText(receiveID)
                    etPw.setText(receivePW)
                }
            }

        //등록 하러 가기 버튼 누르면 회원 가입 버튼 으로 넘어감
        val btnSignUp = findViewById<AppCompatButton>(R.id.signin_toSignup)
        btnSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            activityResultLauncher.launch(intent)
        }

        val btnMain = findViewById<AppCompatButton>(R.id.signin_toMain)
        btnMain.setOnClickListener {
            if (etId.text.toString().isBlank() || etPw.text.toString().isBlank()) {
                Toast.makeText(this, getString(R.string.toast_check_login_info), Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            if (!isValidLogin(etId.text.toString(), etPw.text.toString())) {
                Toast.makeText(this, getString(R.string.toast_failed_login), Toast.LENGTH_SHORT)
                    .show()
            } else {
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
    }

    private fun isValidLogin(Id: String, password: String): Boolean {
        val user = datalist.find { it.userId == Id } ?: return false
        return user.userPw == password
    }
}