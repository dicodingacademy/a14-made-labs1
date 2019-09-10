package com.dicoding.picodiploma.mylistview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.item_hero.view.*

class HeroAdapter internal constructor(private val context: Context) : BaseAdapter() {
    internal var heroes = arrayListOf<Hero>()

    override fun getCount(): Int = heroes.size

    override fun getItem(i: Int): Any = heroes[i]

    override fun getItemId(i: Int): Long = i.toLong()

    override fun getView(position: Int, view: View?, viewGroup: ViewGroup): View {
        var itemView = view
        if (itemView == null) {
            //Menghubungkan ViewHolder dengan View
            itemView = LayoutInflater.from(context).inflate(R.layout.item_hero, viewGroup, false)
        }

        val viewHolder = ViewHolder(itemView as View)

        //Mengubah nilai pahlawan sesuai dari posisinya
        val hero = getItem(position) as Hero
        viewHolder.bind(hero)
        return itemView
    }

     private inner class ViewHolder constructor(private val view: View) {
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
