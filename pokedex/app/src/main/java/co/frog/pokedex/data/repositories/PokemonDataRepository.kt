package co.frog.pokedex.data.repositories

import co.frog.pokedex.data.PokeAppConfig
import co.frog.pokedex.data.http.PokemonApi
import co.frog.pokedex.data.http.RetrofitBuilderService
import javax.inject.Inject

class PokemonDataRepository @Inject constructor() {

    var pokemonApi: PokemonApi = RetrofitBuilderService.getInstance(PokeAppConfig.pokeApiBaseUrl)
        .create(PokemonApi::class.java)

    suspend fun getPokemon() {
        pokemonApi.getPokemon(limit = 10, offset = 0)
    }
}
