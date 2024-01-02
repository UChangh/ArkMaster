package com.android.arkmaster

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.arkmaster.Value.characterId
import com.android.arkmaster.main.CharacterManager.getCharacters
import com.android.arkmaster.mypage.Comments
import com.android.arkmaster.mypage.MyCommentsTempDatas.addComment
import com.android.arkmaster.mypage.MyCommentsTempDatas.getCharacterComment
import com.android.arkmaster.user.SignInActivity.Companion.currentUserId
import com.android.arkmaster.user.UserManager.getUserCharacter
import java.time.LocalDate

class DetailActivity : AppCompatActivity() {
    private val userComment: TextView by lazy {
        findViewById(R.id.us_comment)
    }
    private val userCommentTime: TextView by lazy {
        findViewById(R.id.us_time)
    }

    private val editText: EditText by lazy {
        findViewById(R.id.edt_text)
    }

    private val userEmail: TextView by lazy {
        findViewById(R.id.us_email)
    }

    private val userCharacterImage: ImageView by lazy {
        findViewById(R.id.img_user)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val items = getCharacters().sortedBy { it.korName }
        val korName = findViewById<TextView>(R.id.korName)
        val profileImage = findViewById<ImageView>(R.id.profileImage)
        val type = findViewById<TextView>(R.id.type)
        val engName = findViewById<TextView>(R.id.engName)
        val weapon = findViewById<TextView>(R.id.weapon)
        val charComment = findViewById<TextView>(R.id.char_comment)
        val info = findViewById<TextView>(R.id.info)
        val identityName = findViewById<TextView>(R.id.idenName)
        val identityInfo = findViewById<TextView>(R.id.ideninfo)
        val btnInput = findViewById<Button>(R.id.btn_input) //댓글 입력 버튼


        val characterIndex = intent.getIntExtra(characterId, -1)
        if (characterIndex == -1) {
            finish()
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

        // add
        getComment(items[characterIndex].id)

        btnInput.setOnClickListener {
            val commentText = editText.text.toString().trim()
            if (commentText.isNotEmpty()) {
                addComment(
                    Comments(
                        currentUserId,
                        items[characterIndex].id,
                        commentText,
                        LocalDate.now()
                    )
                )
                editText.text.clear()
                recreate()
            }
        }
    }

    private fun getComment(id: Int) {
        val comments = getCharacterComment(id)
        if (comments.isEmpty()) {
            return
        }

        userComment.visibility = View.VISIBLE
        userEmail.visibility = View.VISIBLE
        userCommentTime.visibility = View.VISIBLE
        userCharacterImage.visibility = View.VISIBLE


        val email = comments[comments.size - 1].userEmail
        val characterImage = getUserCharacter(email)
        userComment.text = comments[comments.size - 1].commentText
        userCommentTime.text = "${comments[comments.size - 1].commentTime}"
        userEmail.text = email

        val imageResId = if (characterImage != 0) {
            getCharacters()[characterImage].profileImage
        } else {
            R.drawable.ic_user
        }
        userCharacterImage.setImageResource(
            imageResId
        )
    }
}

