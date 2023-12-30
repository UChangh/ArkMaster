package com.android.arkmaster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.android.arkmaster.Value.characterId
import com.android.arkmaster.main.CharacterManager
import java.time.LocalDate

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val items = CharacterManager.getItems(applicationContext).sortedBy { it.korName }
        val korName = findViewById<TextView>(R.id.korName)
        val profileImage = findViewById<ImageView>(R.id.profileImage)
        val type = findViewById<TextView>(R.id.type)
        val engName = findViewById<TextView>(R.id.engName)
        val weapon = findViewById<TextView>(R.id.weapon)
        val charComment = findViewById<TextView>(R.id.char_comment)
        val info = findViewById<TextView>(R.id.info)
        val identityName = findViewById<TextView>(R.id.idenName)
        val identityInfo = findViewById<TextView>(R.id.ideninfo)
        val editText = findViewById<EditText>(R.id.edt_text) // 댓글 입력창
        val btnInput = findViewById<Button>(R.id.btn_input) //댓글 입력 버튼
        val userComment = findViewById<TextView>(R.id.us_comment) //댓글 보이는 곳
        val userEmail = findViewById<TextView>(R.id.us_email) //댓글 작성자
        val userCharacterImage = findViewById<ImageView>(R.id.img_user) // 댓글 작성자 대표 캐릭터 이미지
        val userCommentTime = findViewById<TextView>(R.id.us_time) //댓글 작성 시간


        val characterIndex = intent.getIntExtra(characterId, -1)
        if (characterIndex == -1) {
            return
        }

        korName.text = items[characterIndex].korName
        engName.text = items[characterIndex].engName
        profileImage.setImageResource(items[characterIndex].profileImage)

        type.text = items[characterIndex].type
        weapon.text = items[characterIndex].weapon
        charComment.text = items[characterIndex].charComment
        info.text = items[characterIndex].info
        identityName.text = items[characterIndex].identityName
        identityInfo.text = items[characterIndex].identityInfo

        //초기 에는 댓글 입력 요소 및 댓글 표시 안함
        userComment.visibility = View.GONE
        userEmail.visibility = View.GONE
        userCommentTime.visibility = View.GONE
        userCharacterImage.visibility = View.GONE

        btnInput.setOnClickListener {
            val commentText = editText.text.toString().trim()
            if (commentText.isNotEmpty()) {
                userComment.text = editText.text.toString()
                userComment.visibility = View.VISIBLE
                userEmail.visibility = View.VISIBLE
                userCommentTime.visibility = View.VISIBLE
                userCommentTime.text = "${LocalDate.now()}"
                userCharacterImage.visibility = View.VISIBLE
                editText.text = null
            }
        }
    }
}

