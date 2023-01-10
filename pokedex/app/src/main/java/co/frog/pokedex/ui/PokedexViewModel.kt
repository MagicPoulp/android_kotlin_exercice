package co.frog.pokedex.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.frog.pokedex.data.repositories.PokemonDataRepository
import co.frog.pokedex.data.structures.InvalidDataException
import co.frog.pokedex.data.structures.PokemonDetails
import co.frog.pokedex.data.structures.ResultOf
import co.frog.pokedex.domain.ExtractPokemonDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

// https://medium.com/@hamanhduy/test-your-android-mvvm-app-with-hilt-2ad583e49f74
// https://developer.android.com/training/dependency-injection/hilt-testing#replace-binding

@HiltViewModel
class PokedexViewModel @Inject constructor(
    private val pokemonDataRepository: PokemonDataRepository,
    private val extractPokemonDetailsUseCase: ExtractPokemonDetailsUseCase,
) : ViewModel() {

    // https://medium.com/androiddevelopers/migrating-from-livedata-to-kotlins-flow-379292f419fb
    // https://bladecoder.medium.com/kotlins-flow-in-viewmodels-it-s-complicated-556b472e281a
    val pokemonList: StateFlow<ResultOf<List<PokemonDetails>>> =
        flow<ResultOf<List<PokemonDetails>>> {
            while (true) {
                try {
                    val pokemon = fetchPokemon()
                    emit(ResultOf.Success(pokemon))
                    break
                } catch (t: Throwable) {
                    emit(ResultOf.Failure(null, t))
                    // in case of error we retry after a delay
                    delay(10000L)
                }
            }
        }
            .flowOn(Dispatchers.IO)
            .stateIn(
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
