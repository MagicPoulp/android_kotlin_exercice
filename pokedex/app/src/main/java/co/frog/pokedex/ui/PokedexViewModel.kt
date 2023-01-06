package co.frog.pokedex.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.frog.pokedex.data.repositories.PokemonDataRepository
import co.frog.pokedex.data.structures.InvalidDataException
import co.frog.pokedex.data.structures.PokemonDetails
import co.frog.pokedex.data.structures.ResultOf
import co.frog.pokedex.domain.ExtractPokemonDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class PokedexViewModel @Inject constructor(
    private val pokemonDataRepository: PokemonDataRepository,
    private val extractPokemonDetailsUseCase: ExtractPokemonDetailsUseCase,
) : ViewModel() {

    // https://medium.com/androiddevelopers/migrating-from-livedata-to-kotlins-flow-379292f419fb
    val pokemonList: StateFlow<ResultOf<List<PokemonDetails>>> = flow<ResultOf<List<PokemonDetails>>> {
        try {
            val pokemon = fetchPokemon()
            emit(ResultOf.Success(pokemon))
        }
        catch(t: Throwable) {
            emit(ResultOf.Failure(null, t))
        }
    }.stateIn(
        scope = viewModelScope,
        started = WhileSubscribed(500),
        initialValue = ResultOf.Loading()
    )

    suspend fun fetchPokemon(): List<PokemonDetails> {
        val pokemonResults = pokemonDataRepository.getPokemon()
        if (pokemonResults.count < 1) {
            throw InvalidDataException()
        }
        return extractPokemonDetailsUseCase(pokemonResults)
    }
}
