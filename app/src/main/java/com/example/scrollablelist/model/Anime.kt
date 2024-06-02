package com.example.scrollablelist.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.scrollablelist.R

data class Anime(
    @StringRes val judul: Int,
    @DrawableRes val poster: Int,
    @StringRes val description: Int
)

val Animes = listOf(
    Anime(R.string.title_your_name, R.drawable.poster_your_name, R.string.desc_your_name),
    Anime(R.string.title_natsue, R.drawable.poster_natsue, R.string.desc_natsue),
    Anime(R.string.title_suzume, R.drawable.poster_suzume, R.string.desc_suzume),
    Anime(R.string.title_violet, R.drawable.poster_violet, R.string.desc_violet),
    Anime(R.string.title_koe_no_katachi, R.drawable.poster_koe_no_katachi, R.string.desc_koe_no_katachi),
    Anime(R.string.title_i_want_to_eat_your_pancreas, R.drawable.poster_i_want_your_pancreas, R.string.desc_i_want_to_eat_your_pancreas),
    Anime(R.string.title_tenki_no_ko, R.drawable.poster_tenki_no_ko, R.string.desc_tenki_no_ko),
    Anime(R.string.title_no_game_no_life, R.drawable.poster_no_game_no_life, R.string.desc_no_game_no_life),
)

