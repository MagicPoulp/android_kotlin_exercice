package co.frog.pokedex

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.testing.TestLifecycleOwner
import co.frog.pokedex.data.repositories.PokemonDataRepository
import co.frog.pokedex.data.structures.PokemonDetails
import co.frog.pokedex.data.structures.ResultOf
import co.frog.pokedex.data.structures.api.NamedAPIResource
import co.frog.pokedex.data.structures.api.NamedAPIResourceList
import co.frog.pokedex.domain.ExtractPokemonDetailsUseCase
import co.frog.pokedex.ui.PokedexViewModel
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.*
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

@HiltAndroidTest
class PokedexViewModelUnitTest {

    lateinit var pokeViewModel: PokedexViewModel

    private fun getDummyPokemonList(): NamedAPIResourceList {
        val results = listOf(
            NamedAPIResource(
                name = "Pokémon1",
                url = "https://pokeapi.co/api/v2/pokemon/1/",
            )
        )
        return NamedAPIResourceList(
            count = results.size,
            next = null,
            previous = null,
            results = results,
        )
    }

    private val testingPokemonList = getDummyPokemonList()

    @OptIn(ExperimentalCoroutinesApi::class)
    @BeforeEach
    fun beforeEach() {
        // https://github.com/Kotlin/kotlinx.coroutines/blob/master/kotlinx-coroutines-test/MIGRATION.md
        // this dispatcher skips delays
        Dispatchers.setMain(UnconfinedTestDispatcher())
        //Dispatchers.setMain(StandardTestDispatcher())
        val pokemonDataRepository = mock<PokemonDataRepository>() {
            onBlocking { getPokemon() } doReturn testingPokemonList
        }
        val extractPokemonDetailsUseCase = mock<ExtractPokemonDetailsUseCase>()
        pokeViewModel = PokedexViewModel(pokemonDataRepository, extractPokemonDetailsUseCase)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @AfterEach
    fun afterEach() {
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testGetPokemon() = runTest {
        val actual = mutableListOf<ResultOf<List<PokemonDetails>>>()
        val testLifecycleOwner = TestLifecycleOwner()
        testLifecycleOwner.lifecycleScope.launch {
            testLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                pokeViewModel.pokemonList.collect {
                    actual.add(it)
                }
            }
        }
        testLifecycleOwner.currentState = Lifecycle.State.CREATED
        testLifecycleOwner.currentState = Lifecycle.State.STARTED
        runCurrent()
        advanceUntilIdle()
        assertThat("number of values", actual.size, equalTo(2))
        assertThat("number of values", actual.size, equalTo(2))
        assertThat("Loading value", actual[0], equalTo(ResultOf.Loading("2")))
        //assertThat("Success value", actual[1], equalTo(ResultOf.Success(testingPokemonList)))
        //ResultOf.Success(pokemon)
        //assertThat("Success value", actual.get(2), equalTo(ResultOf.Success))

    }
}
