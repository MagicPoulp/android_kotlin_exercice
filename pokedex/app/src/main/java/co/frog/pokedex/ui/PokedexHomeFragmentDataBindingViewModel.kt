package co.frog.pokedex.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.frog.pokedex.data.structures.PokemonDetails
import co.frog.pokedex.data.structures.ResultOf

enum class UiStateHomeFragment {
    LOADING,
    SUCCESS,
    FAILURE,
}

class PokedexHomeFragmentDataBindingViewModel : ViewModel() {
    private val _uiState: MutableLiveData<UiStateHomeFragment> = MutableLiveData(UiStateHomeFragment.LOADING)
    val uiState: LiveData<UiStateHomeFragment>
        get() = _uiState

    private val _pokemonList: MutableLiveData<List<PokemonDetails>> = MutableLiveData()
    val pokemonList: LiveData<List<PokemonDetails>>
        get() = _pokemonList

    fun updateUI(result: ResultOf<List<PokemonDetails>>) {
        when (result) {
            is ResultOf.Loading -> {
                _pokemonList.postValue(emptyList())
                _uiState.postValue(UiStateHomeFragment.LOADING)
            }
            is ResultOf.Success -> {
                _pokemonList.postValue(result.value)
                _uiState.postValue(UiStateHomeFragment.SUCCESS)
            }
            is ResultOf.Failure -> {
                _pokemonList.postValue(emptyList())
                _uiState.postValue(UiStateHomeFragment.FAILURE)
            }
        }
    }
}
