package com.android.arkmaster.main

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.arkmaster.R
import com.android.arkmaster.mypage.MyCommentsTempDatas
import java.util.Locale

class RecyclerCharacterAdapter(
    private var items: List<Character>,
    private val context: Context
) : RecyclerView.Adapter<RecyclerCharacterAdapter.ViewHolder>() {
    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    private lateinit var listener: OnItemClickListener

    fun setItemClickListener(itemClickListener: OnItemClickListener) {
        listener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rv_character, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItem(item: Character) {
            val ivCharacter: ImageView = itemView.findViewById(R.id.ivCharacter)
            val tvName: TextView = itemView.findViewById(R.id.tvCharacterName)
            val tvComment: TextView = itemView.findViewById(R.id.tvCharacterComment)

            with(ivCharacter) {
                setImageResource(item.profileImage)
                clipToOutline = true
            }

            tvName.text = item.korName
            val commentsSize = "${context.getString(R.string.my_comments)} ${
                MyCommentsTempDatas().getCommentSize(item.korName)
            }"
            Log.d("RecyclerCharacterAdapter", commentsSize)
            tvComment.text = commentsSize

            itemView.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(itemView, position)
                }
            }
        }
    }

    fun updateData(newItems: List<Character>) {
        items = newItems
        Log.d("RecyclerCharacterAdapter", "${items[0].weapon}")

        notifyDataSetChanged()
    }

}

