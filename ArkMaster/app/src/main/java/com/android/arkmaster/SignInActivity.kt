package com.android.arkmaster

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.android.arkmaster.main.MainActivity

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
        val etspinner =findViewById<EditText>(R.id.signin_etspinner)

        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                val receiveID = it.data?.getStringExtra("email") ?: ""
                val receivePW = it.data?.getStringExtra("PW") ?: ""
                val receiveSpinner = it.data?.getStringExtra("spinner") ?: ""

                etspinner.setText(receiveSpinner)
                etid.setText(receiveID)
                etpw.setText(receivePW)
            }
        }



        //회원가입 페이지로 넘기기
        val btn_toSignUp = findViewById<Button>(R.id. signin_tosignup)
        btn_toSignUp.setOnClickListener() {
            val intent = Intent(this, SignUpActivity::class.java)
            activityResultLauncher.launch(intent)
        }


        startActivity(Intent(this, MainActivity::class.java))
    }

    fun login(id: String, password: String): Boolean {
        // TODO 로그인 성공시 아이디 저장
        if (isValidLogin(id, password)) {
            currentUserId = id
            return true
        }
        return false
    }

    private fun isValidLogin(id: String, password: String): Boolean {
        // TODO 로그인 유효성 검사
        return true
    }
}