package com.dicoding.picodiploma.mylistview

import android.content.res.TypedArray
import android.os.Bundle
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var dataName: Array<String>
    private lateinit var dataDescription: Array<String>
    private lateinit var dataPhoto: TypedArray
    private lateinit var adapter: HeroAdapter
    private var heroes: ArrayList<Hero> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = HeroAdapter(this)

        lv_list.adapter = adapter

        //Menyipakan data dari resource
        prepare()

        //Menambahkan data dari resource ke adapter
        addItem()

        //Memberi aksi pada lv_list
        lv_list.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            Toast.makeText(this@MainActivity, heroes[position].name, Toast.LENGTH_SHORT).show()
        }
    }

    private fun prepare() {
        dataName = resources.getStringArray(R.array.data_name)
        dataDescription = resources.getStringArray(R.array.data_description)
        dataPhoto = resources.obtainTypedArray(R.array.data_photo)
    }

    private fun addItem() {
        for (position in dataName.indices) {
            val hero = Hero(
                    dataPhoto.getResourceId(position, -1),
                    dataName[position],
                    dataDescription[position]
            )
            heroes.add(hero)
        }
        adapter.setHeroes(heroes)
    }
}
