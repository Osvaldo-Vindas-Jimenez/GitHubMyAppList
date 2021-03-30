package com.example.myapplicationlist.ui.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplicationlist.R
import com.example.myapplicationlist.models.EpisodeModels
import com.example.myapplicationlist.ui.activities.ItemActivity
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.episode_item.view.*
import java.util.*
import kotlin.collections.ArrayList


class EpisodeAdapter(private var episodeList: List<EpisodeModels>, private val context: Context):
    RecyclerView.Adapter<EpisodeAdapter.ViewHolder>(), Filterable {
    /**
    Variable para guardar los episodios
     */
    private val allEpisodeList: List<EpisodeModels> = episodeList
    /**
    Se pasa la información al activity
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.episode_item, parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = episodeList[position]
        holder.titleTV.text = item.title
        holder.descriptionTV.text = item.description.subSequence(0, 50)
            .toString().plus("...")
        holder.smallImg?.let {
            Glide.with(context).load(item.small_artwork_url)
                .error(R.drawable.ic_launcher_background).centerCrop().into(it)
        }
        /**
        Obtiene la información de un ítem seleccionado y la  coloca en forma de string para  cargar la activity
         */
        holder.itemView.setOnClickListener {
            val intent = Intent(context, ItemActivity::class.java)
            intent.putExtra("episode", GsonBuilder().create().toJson(item))
            context.startActivity(intent)
        }
    }



    override fun getFilter(): Filter {
        return object : Filter() {
            /**
            Aquí, la función recibe la consulta de búsqueda y filtra por nombre del título del episodio.
             */
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val filterResults = FilterResults()
                val query = charSequence.toString()

                episodeList = allEpisodeList
                if (query.isNotEmpty()) {
                    val filteredList: ArrayList<EpisodeModels> = ArrayList()
                    for (episode in episodeList)
                        if (episode.title.toLowerCase(Locale.ROOT)
                                .contains(query.toLowerCase(Locale.ROOT))) filteredList.add(episode)
                    episodeList = filteredList
                }

                filterResults.values = episodeList
                return filterResults
            }
            /**
            Procesa todos los cambios a la lista de episodios
             */
            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                episodeList = filterResults.values as List<EpisodeModels>
                notifyDataSetChanged()
            }
        }

    }
    /**
    clase ViewHoldar con la info a pintar en el activity
     */
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var smallImg: ImageView? = itemView.idImage
        var titleTV: TextView = itemView.idTitle
        var descriptionTV: TextView = itemView.idDescription
    }

    override fun getItemCount(): Int = episodeList.size

}

