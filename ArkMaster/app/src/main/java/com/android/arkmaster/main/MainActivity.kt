package com.android.arkmaster.main

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.arkmaster.DetailActivity
import com.android.arkmaster.R
import com.android.arkmaster.Value.characterId
import com.android.arkmaster.main.CharacterManager.getCharacters
import com.android.arkmaster.mypage.MyPageActivity
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var configuration: Configuration
    private lateinit var adapter: RecyclerCharacterAdapter

    private val rcCharacter: RecyclerView by lazy {
        findViewById(R.id.rcCharacter)
    }

    private val spinnerLocale: Spinner by lazy {
        findViewById(R.id.spLocale)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configuration = resources.configuration
        initView()
    }

    override fun onResume() {
        super.onResume()
        updateRecyclerViewData()
    }

    private fun updateRecyclerViewData() {
        val newItems = getCharacters().sortedBy { it.korName }
        adapter.updateData(newItems)
        adapter.notifyDataSetChanged()
    }

    private fun setLocaleChanger() {
        val locales = listOf(
            getString(R.string.locale_korean),
            getString(R.string.locale_english)
        )

        spinnerLocale.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            locales
        )

        spinnerLocale.setSelection(0, false)

        spinnerLocale.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedLocale = when (position) {
                    0 -> Locale.KOREAN
                    1 -> Locale.ENGLISH
                    else -> return
                }

                if (selectedLocale != configuration.locale) {
                    setLocale(selectedLocale)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        findViewById<Button>(R.id.btnMoveMyPage).setOnClickListener {
            startActivity(Intent(this, MyPageActivity::class.java))
        }
    }

    private fun initView() {
        setCharacterAdapter()

        setLocaleChanger()
    }

    private fun setLocale(locale: Locale) {
        configuration.setLocale(locale)
        resources.updateConfiguration(configuration, resources.displayMetrics)
        recreate()
    }

    private fun setCharacterAdapter() {
        val items = getCharacters().sortedBy { it.korName }

        adapter = RecyclerCharacterAdapter(items)
        rcCharacter.adapter = adapter
        rcCharacter.layoutManager = GridLayoutManager(this, 3)

        adapter.setItemClickListener(object : RecyclerCharacterAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
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

