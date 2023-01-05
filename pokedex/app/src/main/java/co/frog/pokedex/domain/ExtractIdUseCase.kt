package co.frog.pokedex.domain

import javax.inject.Inject

class ExtractIdUseCase @Inject constructor() {
    operator fun invoke(url: String): Int {
        val regex = Regex("")
        val matchResult = regex.matchEntire("url")
        return 1
    }
}
