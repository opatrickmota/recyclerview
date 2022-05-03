package com.patrickmota.recyclerview

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var liveAdapter: LiveAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        addDataSource()
    }

    /*
    * Aqui eu chamo o setDataSet para enviar dados para o adapter (que é o que faz o meio de
    * campo, na qual preenche os items do recycler view)
    * */
    private fun addDataSource() {

        val dataSource = DataSource.createDataSet()
        this.liveAdapter.setDataSet(dataSource)

    }

    private fun initRecyclerView() {

        this.liveAdapter = LiveAdapter { live ->
            openLink(live.link)
        }

        //recyclerview.layoutManager = LinearLayoutManager(this@MainActivity)
        //recyclerview.adapter = this.liveAdapter

        /*
        * Forma otimizada do comentario anterior
        *
        * Aqui tambem se usa Unit function igual a classe LiveAdapter
        * */
        recyclerview.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = liveAdapter
        }
    }

    private fun openLink(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}