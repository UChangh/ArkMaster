package com.android.arkmaster.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.arkmaster.DetailActivity
import com.android.arkmaster.R
import com.android.arkmaster.Value.characterId
import com.android.arkmaster.mypage.MyPageActivity

class MainActivity : AppCompatActivity() {

    private val rcCharacter: RecyclerView by lazy { findViewById(R.id.rcCharacter) }
//    private val searchView: SearchView by lazy { findViewById(R.id.searchView) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()

        startActivity(Intent(this, MyPageActivity::class.java))
    }

    private fun initView() {
        val items = CharacterManager.getItems().sortedBy { it.korName }

        val adapter = RecyclerCharacterAdapter(items)
        rcCharacter.adapter = adapter
        rcCharacter.layoutManager = GridLayoutManager(this, 3)

//        val searchViewTextListener: SearchView.OnQueryTextListener =
//            object : SearchView.OnQueryTextListener {
//                override fun onQueryTextSubmit(s: String): Boolean {
//                    return false
//                }
//
//                override fun onQueryTextChange(s: String): Boolean {
//                    Log.d("MainActivity", "QueryTextChange: $s")
//                    adapter.filter.filter(s)
//                    return false
//                }
//            }
//
//        searchView.setOnQueryTextListener(searchViewTextListener)

        adapter.setItemClickListener(object : RecyclerCharacterAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                showToast("$position")
                startDetailActivity(view, position)
            }
        })
    }

    private fun showToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    private fun startDetailActivity(view: View, position: Int) {
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra(characterId, position)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        val transitionPairs = getTransitionPairs(view)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this, *transitionPairs.toTypedArray()
        )

        startActivity(intent, options.toBundle())
    }

    private fun getTransitionPairs(view: View): List<Pair<View, String>> {
        val characterPair = Pair<View, String>(view.findViewById(R.id.ivCharacter), "transImage")
        val characterNamePair =
            Pair<View, String>(view.findViewById(R.id.tvCharacterName), "transName")

        return listOf(characterPair, characterNamePair)
    }
}
