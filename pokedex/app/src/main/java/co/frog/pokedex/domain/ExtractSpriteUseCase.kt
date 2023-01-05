package co.frog.pokedex.domain

import javax.inject.Inject

class ExtractSpriteUseCase @Inject constructor() {
    operator fun invoke(id: Int): String {
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
    }
}
