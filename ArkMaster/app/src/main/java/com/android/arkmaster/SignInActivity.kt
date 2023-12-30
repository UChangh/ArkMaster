package com.android.arkmaster

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import com.android.arkmaster.main.MainActivity
import com.android.arkmaster.mypage.SampleUserData

class SignInActivity : AnimationActivity(TransitionMode.HORIZON) {

    companion object {
        var currentUserId: String? = null
    }

    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        //회원가입 페이지에서 ID, PW, Spinner 가지고 오기.
        val etid = findViewById<EditText>(R.id.signin_etid)
        val etpw = findViewById<EditText>(R.id.signin_etpw)
        val etspinner = findViewById<EditText>(R.id.signin_etspinner)

        activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == RESULT_OK) {
                    val receiveID = it.data?.getStringExtra("email") ?: ""
                    val receivePW = it.data?.getStringExtra("PW") ?: ""
                    val receiveSpinner = it.data?.getStringExtra("spinner") ?: ""

                    etspinner.setText(receiveSpinner)
                    etid.setText(receiveID)
                    etpw.setText(receivePW)
                }
            }

        fun login(id: String, password: String): Boolean {
            // TODO 로그인 성공시 아이디 저장
            if (isValidLogin(id, password)) {
                currentUserId = id
                return true
            }
            return false
        }

        //등록하러가기 버튼 누르면 회원가입 버튼으로 넘어감
        val btnSignUp = findViewById<AppCompatButton>(R.id.signin_toSignup)
        btnSignUp.setOnClickListener() {
            val intent = Intent(this, SignUpActivity::class.java)
            activityResultLauncher.launch(intent)
        }


        val btnMain = findViewById<AppCompatButton>(R.id.signin_toMain)
        btnMain.setOnClickListener() {
            if (etid.text.toString().isBlank() || etpw.text.toString().isBlank()) {
                Toast.makeText(this, "이메일과 비밀번호를 모두 입력해주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (!isValidLogin(etid.text.toString(), etpw.text.toString())) {
                Toast.makeText(this, "없는 회원 정보이거나 비밀번호가 옳지 않습니다.", Toast.LENGTH_SHORT).show()
            } else {
                startActivity(Intent(this, MainActivity::class.java))
            }
        }


            SampleUserData().sampleUserData()    // 샘플 데이터 삽입
        }
    private fun isValidLogin(Id: String, password: String): Boolean {
        val user = datalist.find { it.userId == Id }
        if (user == null) {
            return false
        }
        return user.userPw == password
    }
}