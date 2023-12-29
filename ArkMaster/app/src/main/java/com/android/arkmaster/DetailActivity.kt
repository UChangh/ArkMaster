package com.android.arkmaster

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.android.arkmaster.Value.characterId
import com.android.arkmaster.main.CharacterManager
import com.android.arkmaster.main.MainActivity
import com.android.arkmaster.mypage.MyCommentsTempDatas
import com.android.arkmaster.mypage.MyPageActivity
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
        korName.setText(items.get(characterIndex).korName)
        engName.setText(items.get(characterIndex).engName)
        profileImage.setImageResource(items.get(characterIndex).profileImage)
        type.setText(items.get(characterIndex).type)
        weapon.setText(items.get(characterIndex).weapon)
        char_comment.setText(items.get(characterIndex).char_comment)
        info.setText(items.get(characterIndex).info)
        idenName.setText(items.get(characterIndex).idenName)
        ideninfo.setText(items.get(characterIndex).ideninfo)

        val btn_home = findViewById<Button>(R.id.btn_home)
        btn_home.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val btn_mypage = findViewById<Button>(R.id.btn_mypage)
        btn_mypage.setOnClickListener {
            val intent = Intent(this, MyPageActivity::class.java)
        }
    }
}