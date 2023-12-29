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
import com.android.arkmaster.databinding.ActivityMyPageBinding
import java.time.LocalDate

class MyPageActivity:AppCompatActivity() {
    private lateinit var binding: ActivityMyPageBinding
    // 깃허브 ㅅㅂ
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyPageBinding.inflate(layoutInflater)
        val myPage = binding.root
        setContentView(myPage)

        val mctd = MyCommentsTempDatas()

        // E메일, 캐릭터명 = 모든 캐릭터의 댓글에서 해당 유저가 입력한 댓글의 캐릭터명, 댓글, 날짜를 가져오기(디테일 페이지)
        mctd.dataset.add(Comments("E메일", "캐릭터명", "엄청 무서운 포즈 어흥", "${LocalDate.now()}"))
        binding.myCommentsRecyclerView.adapter = MyCommentsRecyclerAdapter(mctd.dataset)

        // 스피너 팝업 띄우는 부분
        binding.tvSpinnerPopup.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val inflater = this.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = inflater.inflate(R.layout.set_main_character_popup_layout, null)
            val spinners = view.findViewById<Spinner>(R.id.char_list_spinner)
            val adapter = ArrayAdapter.createFromResource(this, R.array.characters_name, android.R.layout.simple_spinner_item)

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinners.adapter = adapter
            spinners.setSelection(0)

            builder.setTitle(resources.getString(R.string.set_main_character_popup))
                .setView(view)
                .create()
                .show()
            spinners.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    val str = resources.getStringArray(R.array.characters_name)
                    binding.tvSpinnerPopup.text = str[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }
        }
    }

    private fun toast(str:String) {
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show()
    }
}