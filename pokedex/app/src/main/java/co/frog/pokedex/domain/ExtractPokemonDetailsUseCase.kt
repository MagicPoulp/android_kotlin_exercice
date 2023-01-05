package co.frog.pokedex.domain

import co.frog.pokedex.data.structures.api.NamedAPIResourceList
import co.frog.pokedex.data.structures.PokemonDetails
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
            val pokemonDetails = PokemonDetails(
                name = result.name,
                id = id,
                spriteUrl = getSpriteUrlUseCase(id),
            )
            parsedResults.add(pokemonDetails)
        }
        return parsedResults.toList()
    }
}
