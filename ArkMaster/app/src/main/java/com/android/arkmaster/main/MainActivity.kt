package com.android.arkmaster.main

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.arkmaster.DetailActivity
import com.android.arkmaster.R
import com.android.arkmaster.Value.characterId
import java.util.Locale


class MainActivity : AppCompatActivity() {
    private lateinit var configuration: Configuration
    private lateinit var adapter: RecyclerCharacterAdapter

    private val rcCharacter: RecyclerView by lazy { findViewById(R.id.rcCharacter) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()

        configuration = resources.configuration

        val buttonKorea = findViewById<Button>(R.id.btnMoveHome)
        buttonKorea.setOnClickListener {
            configuration.setLocale(Locale.KOREAN)
            resources.updateConfiguration(configuration, resources.displayMetrics)
            updateRecyclerViewData()
            recreate()
        }

        val buttonUS = findViewById<Button>(R.id.btnMoveMyPage)
        buttonUS.setOnClickListener {
            configuration.setLocale(Locale.ENGLISH)
            resources.updateConfiguration(configuration, resources.displayMetrics)

            updateRecyclerViewData()
            recreate()
        }
    }

    private fun updateRecyclerViewData() {
        val newItems = CharacterManager.getItems(applicationContext).sortedBy { it.korName }
        adapter.updateData(newItems)
    }

    private fun initView() {
        val items = CharacterManager.getItems(applicationContext).sortedBy { it.korName }

        adapter = RecyclerCharacterAdapter(items, applicationContext)
        rcCharacter.adapter = adapter
        rcCharacter.layoutManager = GridLayoutManager(this, 3)

        adapter.setItemClickListener(object : RecyclerCharacterAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                // Toast.makeText(applicationContext, "$position", Toast.LENGTH_SHORT).show()
                startDetailActivity(view, position)
            }
        })
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