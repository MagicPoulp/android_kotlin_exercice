package co.frog.pokedex.domain

import javax.inject.Inject

class ExtractIdUseCase @Inject constructor() {
    operator fun invoke(url: String): Int {
        return 1
    }
}
