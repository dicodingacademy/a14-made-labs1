package com.dicoding.picodiploma.mylistview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

import java.util.ArrayList

class HeroAdapter internal constructor(private val context: Context) : BaseAdapter() {
    private var heroes:MutableList<Hero> = mutableListOf()

    internal fun setHeroes(heroes: MutableList<Hero>) {
        this.heroes = heroes
    }

    override fun getCount(): Int = heroes.size


    override fun getItem(i: Int): Any = heroes[i]


    override fun getItemId(i: Int): Long = i.toLong()

    override fun getView(i: Int, view: View?, viewGroup: ViewGroup): View {
        var view = view
        if (view == null) {
            //Menghubungkan ViewHolder dengan View
            view = LayoutInflater.from(context).inflate(R.layout.item_hero, viewGroup, false)
        }

        val viewHolder = ViewHolder(view as View)

        //Mengubah nilai pahlawan sesuai dari posisinya
        val hero = getItem(i) as Hero
        viewHolder.bind(hero)
        return view
    }

    private inner class ViewHolder internal constructor(view: View) {
        private val txtName: TextView = view.findViewById(R.id.txt_name)
        private val txtDescription: TextView = view.findViewById(R.id.txt_description)
        private val imgPhoto: ImageView = view.findViewById(R.id.img_photo)

        internal fun bind(hero: Hero) {
            txtName.text = hero.name
            txtDescription.text = hero.description
            imgPhoto.setImageResource(hero.photo)
        }
    }
}
