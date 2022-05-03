package com.patrickmota.recyclerview

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

        this.liveAdapter = LiveAdapter()

        recyclerview.layoutManager = LinearLayoutManager(this@MainActivity)
        recyclerview.adapter = this.liveAdapter
    }
}