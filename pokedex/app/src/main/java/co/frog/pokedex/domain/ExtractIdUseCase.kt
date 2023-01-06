package co.frog.pokedex.domain

import co.frog.pokedex.data.structures.InvalidDataException
import java.lang.Integer.parseInt
import javax.inject.Inject

class ExtractIdUseCase @Inject constructor() {
    operator fun invoke(url: String): Int {
        val regex = Regex("https://pokeapi.co/api/v2/pokemon/(\\d+)/")
        val matchResult = regex.matchEntire(url)
        return matchResult?.groups?.get(1)?.value?.let { parseInt(it) } ?: throw InvalidDataException()
    }
}
