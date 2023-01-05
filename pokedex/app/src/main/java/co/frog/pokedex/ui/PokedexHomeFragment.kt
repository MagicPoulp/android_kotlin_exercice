package co.frog.pokedex.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import co.frog.pokedex.R
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@AndroidEntryPoint
class PokedexHomeFragment : Fragment(R.layout.pokedex_home_fragment) {

    // the following makes a Hilt deprecation warning disappear
    // https://github.com/google/dagger/issues/3601
    @Inject @ApplicationContext
    lateinit var applicationContext: Context

    private val viewModel by viewModels<PokedexViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchPokemon()
    }
}
