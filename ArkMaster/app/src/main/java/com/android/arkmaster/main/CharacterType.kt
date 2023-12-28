package com.android.arkmaster.main

import androidx.annotation.StringRes
import com.android.arkmaster.R

enum class CharacterType(
    @StringRes val type: Int
) {
    TYPE_WIZARD(R.string.type_wizard),
    TYPE_MARTIAL_ARTIST(R.string.type_martial_artist),
    TYPE_ASSASSIN(R.string.type_assassin),
    TYPE_HUNTER_WOMAN(R.string.type_hunter_woman),
    TYPE_HUNTER_MAN(R.string.type_hunter_man),
    TYPE_SOLDIER(R.string.type_soldier)

}