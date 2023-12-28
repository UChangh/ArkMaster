package com.android.arkmaster.main

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.android.arkmaster.R
import com.android.arkmaster.mypage.MyCommentsTempDatas

class RecyclerCharacterAdapter(private val items: List<Character>) :
    RecyclerView.Adapter<RecyclerCharacterAdapter.ViewHolder>() {
//    private val filteredCharacter = ArrayList<Character>()
//
//    init {
//        filteredCharacter.addAll(items)
//    }
//
//    override fun getFilter(): Filter {
//        return CharacterFilter()
//    }

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
            val commentsSize = "댓글 ${item.korName.getSize()}"
            tvComment.text = commentsSize

            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(itemView, position)
                }
            }
        }
    }

    // Filter
//    inner class CharacterFilter : Filter() {
//        override fun performFiltering(constraint: CharSequence?): FilterResults {
//            val filterString = constraint.toString()
//            val results = FilterResults()
//
//            // Copy the current filtered list to filter against
//            val filterList: ArrayList<Character> = ArrayList<Character>()
//
//            return if (filterString.isBlank()) {
//                // No filter constraint so return the original list
//                results.values = items
//                results.count = items.size
//                results
//            } else {
//                // Filter the original data
//                for (item in items) {
//                    if (item.korName.contains(filterString)) {
//                        filterList.add(item)
//                    }
//                }
//
//                results.values = filterList
//                results.count = filterList.size
//                results
//            }
//        }
//        @SuppressLint("NotifyDataSetChanged")
//        override fun publishResults(constraint: CharSequence?, results: FilterResults) {
//            Log.d("CharacterFilter", "Filtered Character Count: ${results.count}")
//            filteredCharacter.clear()
//            filteredCharacter.addAll(results.values as ArrayList<Character>)
//
//            // Use runOnUiThread to make UI changes
//            (itemView.context as AppCompatActivity).runOnUiThread {
//                notifyDataSetChanged()
//            }
//        }
//    }
//

    private fun String.getSize() = MyCommentsTempDatas().getCommentSize(this)
}
