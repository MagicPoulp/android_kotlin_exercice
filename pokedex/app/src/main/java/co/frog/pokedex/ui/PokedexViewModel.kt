package co.frog.pokedex.ui

import androidx.lifecycle.ViewModel
import co.frog.pokedex.data.repositories.PokemonDataRepository
import co.frog.pokedex.domain.ExtractIdUseCase
import co.frog.pokedex.domain.ExtractSpriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class PokedexViewModel @Inject constructor(
    private val pokemonDataRepository: PokemonDataRepository,
) : ViewModel() {

    @Inject
    lateinit var extractIdUseCase: ExtractIdUseCase

    @Inject
    lateinit var extractSpriteUseCase: ExtractSpriteUseCase

    fun fetchPokemon() {
        runBlocking {
            val temp = pokemonDataRepository.getPokemon()
            val temp2 = 2
        }
    }
}
