package com.android.arkmaster.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.android.arkmaster.R
import com.android.arkmaster.SignInActivity.Companion.currentUserId
import com.android.arkmaster.databinding.ActivityMyPageBinding
import com.android.arkmaster.datalist
import com.android.arkmaster.main.CharacterManager

class MyPageActivity:AppCompatActivity() {
    private lateinit var binding: ActivityMyPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyPageBinding.inflate(layoutInflater)
        val myPage = binding.root
        setContentView(myPage)

        val mctd = MyCommentsTempDatas()

        // E메일, 캐릭터명, 댓글, 날짜 = 모든 캐릭터의 댓글에서 해당 유저가 입력한 댓글의 캐릭터명, 댓글, 날짜를 가져오기(디테일 페이지)

        binding.myCommentsRecyclerView.adapter = MyCommentsRecyclerAdapter(mctd.dataset)
        val currentUserDatas = datalist.find { it.userId == currentUserId } // 현재 접속중인 유저의 정보

        binding.tvUserName.text = currentUserDatas?.nickname?:"어흥"
        binding.tvUserEmail.text = currentUserDatas?.userId?:"엄청나게 무서운 포즈 어흥"
        binding.mainCharImg.setImageResource(currentUserDatas?.representativeCharacter?:R.drawable.common)

        // 스피너 팝업 띄우는 부분
        binding.tvSpinnerPopup.setOnClickListener {
            val inflater = this.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = inflater.inflate(R.layout.set_main_character_popup_layout, null)
            val spinners = view.findViewById<Spinner>(R.id.char_list_spinner)

//            val adapter = ArrayAdapter.createFromResource(this, R.array.characters_name, android.R.layout.simple_spinner_item)
            val charaNameLists = ArrayList<String>()

            CharacterManager.getItems(applicationContext).let { it.forEach { name -> charaNameLists.add(name.korName) } }
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, charaNameLists)

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinners.adapter = adapter
            spinners.setSelection(0)

            AlertDialog
                .Builder(this)
                .setTitle(resources.getString(R.string.set_main_character_popup))
                .setView(view)
                .create()
                .show()

            spinners.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    binding.tvSpinnerPopup.text = charaNameLists[position]
                    CharacterManager.getItems(applicationContext).find { it.korName == charaNameLists[position] }?.let { image ->
                        currentUserDatas.let {
                            binding.mainCharImg.setImageResource(image.profileImage)
                            it?.representativeCharacter = image.profileImage
                        }
                    }
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
        }

        binding.homeButton.setOnClickListener{
            finish()
        }
    }

    private fun toast(str:String) {
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show()
    }
}