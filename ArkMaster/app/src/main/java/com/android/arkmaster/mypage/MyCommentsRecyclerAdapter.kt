package com.android.arkmaster.mypage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.arkmaster.databinding.MyPageCommentsRecyclerViewLayoutBinding
import com.android.arkmaster.main.CharacterManager.findCharacterName

class MyCommentsRecyclerAdapter(private val comments:MutableList<Comments>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    inner class Holder(binding: MyPageCommentsRecyclerViewLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val id = binding.myCommentCharacterId
        val comment = binding.myCommentText
        val time = binding.myCommentDate
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = MyPageCommentsRecyclerViewLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val hold = holder as Holder
        hold.id.text = findCharacterName(comments[position].characterId)
        hold.comment.text = comments[position].commentText
        hold.time.text = "${comments[position].commentTime}"
    }

    override fun getItemCount(): Int = comments.size
}