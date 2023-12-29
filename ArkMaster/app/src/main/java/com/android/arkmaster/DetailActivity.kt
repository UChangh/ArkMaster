package com.android.arkmaster

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.android.arkmaster.Value.characterId
import com.android.arkmaster.main.CharacterManager
import com.android.arkmaster.main.MainActivity
import com.android.arkmaster.mypage.MyCommentsTempDatas
import com.android.arkmaster.mypage.MyPageActivity
import java.time.LocalDate
import kotlin.math.log

class DetailActivity : AppCompatActivity() {
    //test!!!!!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val items = CharacterManager.getItems().sortedBy { it.korName }
        val korName = findViewById<TextView>(R.id.korName)
        val profileImage = findViewById<ImageView>(R.id.profileImage)
        val type = findViewById<TextView>(R.id.type)
        val engName = findViewById<TextView>(R.id.engName)
        val weapon = findViewById<TextView>(R.id.weapon)
        val char_comment = findViewById<TextView>(R.id.char_comment)
        val info = findViewById<TextView>(R.id.info)
        val idenName = findViewById<TextView>(R.id.idenName)
        val ideninfo = findViewById<TextView>(R.id.ideninfo)

        val characterIndex = intent.getIntExtra(characterId, -1)
        Toast.makeText(this, "$characterIndex", Toast.LENGTH_SHORT).show()
        korName.text=items.get(characterIndex).korName
        engName.text=items.get(characterIndex).engName
        profileImage.setImageResource(items.get(characterIndex).profileImage)
        type.text = items.get(characterIndex).type
        weapon.text = items.get(characterIndex).weapon
        char_comment.text = items.get(characterIndex).char_comment
        info.text = items.get(characterIndex).info
        idenName.text = items.get(characterIndex).idenName
        ideninfo.text = items.get(characterIndex).ideninfo


        //댓글 입력창
        val edt_text = findViewById<EditText>(R.id.edt_text)
        //댓글 입력 버튼
        val btn_input = findViewById<Button>(R.id.btn_input)
        //댓글 보이는 곳
        val us_comment = findViewById<TextView>(R.id.us_comment)
        //댓글 작성자
        val us_email = findViewById<TextView>(R.id.us_email)
        //댓글 작성자 이미지?
        val img_user = findViewById<ImageView>(R.id.img_user)
        //댓글 작성 시간
        val us_time = findViewById<TextView>(R.id.us_time)

        //초기에는 댓글 입력 요소 및 댓글 표시x
        us_comment.visibility = View.GONE
        us_email.visibility = View.GONE
        us_time.visibility = View.GONE
        img_user.visibility = View.GONE
//        val list = MyCommentsTempDatas().getComment(items.get(characterIndex).korName)

        btn_input.setOnClickListener {
            val commentText = edt_text.text.toString().trim()
            if (commentText.isNotEmpty()){
                us_comment.text = "${edt_text.text.toString()}"
                us_comment.visibility = View.VISIBLE
                us_email.visibility = View.VISIBLE
                us_time.visibility = View.VISIBLE
                //us_time.setText("${LocalDate.now()}")
                us_time.text = "${LocalDate.now()}"
                img_user.visibility = View.VISIBLE
                //버튼 누르고 나면 EditText 텍스트 삭제
                edt_text.text = null
            }
        }
    }
}