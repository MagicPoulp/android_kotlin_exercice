package co.frog.pokedex.domain

import android.graphics.BitmapFactory
import co.frog.pokedex.data.structures.api.NamedAPIResourceList
import co.frog.pokedex.data.structures.PokemonDetails
import java.net.URL
import javax.inject.Inject

class ExtractPokemonDetailsUseCase @Inject constructor() {
    @Inject
    lateinit var extractIdUseCase: ExtractIdUseCase

    @Inject
    lateinit var getSpriteUrlUseCase: GetSpriteUrlUseCase

    operator fun invoke(pokemonResults: NamedAPIResourceList): List<PokemonDetails> {
        val parsedResults: MutableList<PokemonDetails> = mutableListOf()
        for (result in pokemonResults.results) {
            val id = extractIdUseCase(result.url)
            val spriteUrl = getSpriteUrlUseCase(id)
            val sprite = BitmapFactory.decodeStream(URL(spriteUrl).openConnection().getInputStream());
            val pokemonDetails = PokemonDetails(
                name = result.name,
                id = id,
                spriteUrl = spriteUrl,
                sprite = sprite
            )
            parsedResults.add(pokemonDetails)
        }
        return parsedResults.toList()
    }
}
