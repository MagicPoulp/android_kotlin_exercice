package co.frog.pokedex.data.structures

import android.graphics.Bitmap

data class PokemonDetails(
    val name: String,
    val id: Int,
    val spriteUrl: String,
    val sprite: Bitmap
)
