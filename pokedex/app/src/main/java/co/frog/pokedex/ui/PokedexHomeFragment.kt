package co.frog.pokedex.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import co.frog.pokedex.R
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class PokedexHomeFragment : Fragment(R.layout.pokedex_home_fragment) {

    // the following makes a Hilt deprecation warning disappear
    // https://github.com/google/dagger/issues/3601
    @Inject @ApplicationContext
    lateinit var applicationContext: Context

    private val viewModel by viewModels<PokedexViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.pokemonList.collect {
                    val temp = it
                    val temp2 = 2
                }
            }
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}
