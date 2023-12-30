package com.android.arkmaster.main

import com.android.arkmaster.R

import android.content.Context

class CharacterManager {
    companion object {
        fun getItems(context: Context): ArrayList<Character> {
            val characterImages = initializeCharacterImages()
            val characterNames = context.getStringArray(R.array.character_names)
            val characterNamesEnglish = context.getStringArray(R.array.character_names_english)
            val characterTypes = context.getStringArray(R.array.character_types)
            val characterComments = context.getStringArray(R.array.character_comments)
            val characterDescriptions = context.getStringArray(R.array.character_descriptions)
            val characterWeapons = context.getStringArray(R.array.character_weapons)
            val characterEnergyTypes = context.getStringArray(R.array.character_energy_types)
            val characterSpecialSkills = context.getStringArray(R.array.character_special_skills)

            val character = ArrayList<Character>()
            for (i in characterNames.indices) {
                character.add(
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

            return character
        }

        private fun Context.getStringArray(resId: Int): Array<String> =
            resources.getStringArray(resId)

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
    }
}