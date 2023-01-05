package co.frog.pokedex.data.http

import co.frog.pokedex.data.structures.api.NamedAPIResourceList
import retrofit2.http.GET
import retrofit2.http.Query

// https://pokeapi.co/docs/v2#pokemon
// https://pokeapi.co/api/v2/pokemon/1/
interface PokemonApi {

    /**
     * Returns the list of pokemons
     */
    @GET("pokemon")
    suspend fun getPokemon(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): NamedAPIResourceList

}
