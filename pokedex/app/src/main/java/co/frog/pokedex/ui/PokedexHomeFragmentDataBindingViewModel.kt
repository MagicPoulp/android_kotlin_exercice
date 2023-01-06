package co.frog.pokedex.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.frog.pokedex.data.structures.PokemonDetails
import co.frog.pokedex.data.structures.ResultOf

class PokedexHomeFragmentDataBindingViewModel : ViewModel() {
    private val _pokemonList: MutableLiveData<List<PokemonDetails>> = MutableLiveData()
    val pokemonList: LiveData<List<PokemonDetails>>
        get() = _pokemonList

    fun updateUI(result: ResultOf<List<PokemonDetails>>) {
        when (result) {
            is ResultOf.Success -> _pokemonList.postValue(result.value)
            else -> {}
        }
    }
}
