package com.dicoding.picodiploma.mylistview

import android.content.res.TypedArray
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var dataName: Array<String>
    private lateinit var dataDescription: Array<String>
    private lateinit var dataPhoto: TypedArray
    private lateinit var adapter: HeroAdapter
    private var heroes:MutableList<Hero> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = HeroAdapter(this)

        val listView = findViewById<ListView>(R.id.lv_list)
        listView.adapter = adapter

        //Menyipakan data dari resource
        prepare()

        //Menambahkan data dari resource ke adapter
        addItem()

        //Memberi aksi pada listView
        listView.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l ->
            Toast.makeText(this@MainActivity, heroes[i].name, Toast.LENGTH_SHORT).show()
        }
    }

    private fun prepare() {
        dataName = resources.getStringArray(R.array.data_name)
        dataDescription = resources.getStringArray(R.array.data_description)
        dataPhoto = resources.obtainTypedArray(R.array.data_photo)
    }

    private fun addItem() {
        for (i in dataName.indices) {
            val hero = Hero(
                    dataPhoto.getResourceId(i, -1),
                    dataName[i],
                    dataDescription[i]
            )
            heroes.add(hero)
        }
        adapter.setHeroes(heroes)
    }
}
