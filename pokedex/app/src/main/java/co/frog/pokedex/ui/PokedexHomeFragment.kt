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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    private val dataBindingViewModel by viewModels<PokedexHomeFragmentDataBindingViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // benefits of doing this sophistication:
        // a LiveData is observed on the main thread, not a flow with repeatOnLifecycle
        // compared to launch, we do not waste a coroutine resource when stopped (in background)
        // we wait a little for screen rotation to avoid interruption (with WhileSubscribed)
        // a StateIn is produced only once
        // https://medium.com/androiddevelopers/migrating-from-livedata-to-kotlins-flow-379292f419fb
        // https://bladecoder.medium.com/kotlins-flow-in-viewmodels-it-s-complicated-556b472e281a
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.pokemonList.collect {
                    dataBindingViewModel.updateUI(it)
                }
            }
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerview = view.findViewById<RecyclerView>(R.id.rvPokemon)
        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(activity)

        // This will pass the ArrayList to our Adapter
        val adapter = PokedexRecyclerViewAdapter()

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter

        dataBindingViewModel.pokemonList.observe(viewLifecycleOwner) { list ->
            (recyclerview.adapter as PokedexRecyclerViewAdapter).setData(list)
        }
    }
}
