package com.android.arkmaster.main

import android.content.Context
import com.android.arkmaster.R

object CharacterRepository {
    private var characters = ArrayList<Character>()

    fun Context.getCharacters(): ArrayList<Character> {
        val characterImages = initializeCharacterImages()
        val characterNames = resources.getStringArray(R.array.character_names)
        val characterNamesEnglish =
            resources.getStringArray(R.array.character_names_english)
        val characterTypes = resources.getStringArray(R.array.character_types)
        val characterComments = resources.getStringArray(R.array.character_comments)
        val characterDescriptions = resources.getStringArray(R.array.character_descriptions)
        val characterWeapons = resources.getStringArray(R.array.character_weapons)
        val characterEnergyTypes = resources.getStringArray(R.array.character_energy_types)
        val characterSpecialSkills =
            resources.getStringArray(R.array.character_special_skills)

        for (i in characterNames.indices) {
            characters.add(
                Character(
                    i,
                    characterImages[i],
                    characterNames[i],
                    characterNamesEnglish[i],
                    characterTypes[i],
                    characterComments[i],
                    characterDescriptions[i],
                    characterWeapons[i],
                    characterEnergyTypes[i],
                    characterSpecialSkills[i]
                )
            )
        }

        return characters
    }

    private fun initializeCharacterImages() = listOf(
        R.drawable.character_breaker,
        R.drawable.character_sorceress,
        R.drawable.character_striker,
        R.drawable.character_reaper,
        R.drawable.character_gunslinger,
        R.drawable.character_bard,
        R.drawable.character_summoner,
        R.drawable.character_blade,
        R.drawable.character_lance_master,
        R.drawable.character_arcana,
        R.drawable.character_holyknight
    )

    fun findCharacterName(id: Int): String {
        return characters.find { it.id == id }?.korName ?: ""
    }
}
