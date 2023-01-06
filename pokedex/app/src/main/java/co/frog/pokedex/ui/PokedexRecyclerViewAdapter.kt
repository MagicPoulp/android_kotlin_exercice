package co.frog.pokedex.ui

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.frog.pokedex.R
import co.frog.pokedex.data.structures.PokemonDetails
import java.net.URL

class PokedexRecyclerViewAdapter : RecyclerView.Adapter<PokedexRecyclerViewAdapter.ViewHolder>() {

    private val mList: MutableList<PokemonDetails> = mutableListOf()

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokedex_row, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val pokemonDetails = mList[position]

        // sets the image to the imageview from our itemHolder class
        holder.imageView.setImageBitmap(pokemonDetails.sprite);

        // sets the text to the textview from our itemHolder class
        holder.textView.text = pokemonDetails.name
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    fun setData(list: List<PokemonDetails>) {
        mList.clear()
        mList.addAll(list);
        notifyDataSetChanged();
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.pokemonImage)
        val textView: TextView = itemView.findViewById(R.id.rowLabel)
    }
}
