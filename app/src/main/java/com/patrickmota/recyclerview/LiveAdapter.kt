package com.patrickmota.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.patrickmota.recyclerview.models.Live
import kotlinx.android.synthetic.main.res_item_live.view.*

class LiveAdapter(private val onItemClicked : (Live) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items: List<Live> = ArrayList()

    /*
    * Eh chamado toda vez que um item da ricycler view for criada,
    * na qual infla o layout que a gente quer, o R.layout.etc
    * */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return LiveViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.res_item_live, parent, false)
        )
    }

    /*
    * Eh aqui que de fato popula as informacoes
    * */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        /*
        * Por poder ter mais de um tipo de view holder a gente faz esse when com is
        * A gente passa o items com a posicao certa para fazer o bind certo,
        * e assim preencher os valores (o bind eh dessa classe)
        * */
        when (holder) {
            is LiveViewHolder -> {
                holder.bind(items[position], onItemClicked)
            }
        }
    }

    /*
    * Para o recycler view ja saber quantidade de items que vai ter*/
    override fun getItemCount(): Int {
        return items.size
    }

    /*
    * Aqui que sera feito o preenchimento de dados para ser listados
    * */
    fun setDataSet(lives: List<Live>) {
        this.items = lives
    }

    /*
     * Live holder é uma representação do XML no Kotlin
     * */
    class LiveViewHolder constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        private val liveTitle = itemView.title
        private val liveAuthor = itemView.author
        private val liveThumbnail = itemView.thumbnail

        /*
        * Pega as informações da model que vai ser passada para ele e coloca no layout
        * do item do recycler view
        * */
        fun bind(live: Live, onItemClicked: (Live) -> Unit) {

            liveTitle.text = live.title
            liveAuthor.text = live.author

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(live.thumbnailUrl)
                .into(liveThumbnail)
            itemView.setOnClickListener{
                onItemClicked(live)
            }
        }

    }
}