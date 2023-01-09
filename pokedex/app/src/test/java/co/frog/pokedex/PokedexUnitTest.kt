package co.frog.pokedex

import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/*
class FakePokemonDataRepository : PokemonDataRepository() {
    override suspend fun getPokemon(): NamedAPIResourceList {
        val results = listOf(
            NamedApiResource(
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
}
*/

/**
 * Test suite
 */
class PokedexUnitTest {

    @BeforeEach
    fun beforeEach() {

    }

    @Test
    fun addition_isCorrect() = runTest {
        assertEquals(4, 2 + 2)
    }
}
