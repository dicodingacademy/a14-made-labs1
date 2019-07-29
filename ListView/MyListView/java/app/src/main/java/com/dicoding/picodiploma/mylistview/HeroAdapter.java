package com.dicoding.picodiploma.mylistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class HeroAdapter extends BaseAdapter {

    private final Context context;
    private ArrayList<Hero> heroes;

    void setHeroes(ArrayList<Hero> heroes) {
        this.heroes = heroes;
    }

    HeroAdapter(Context context) {
        this.context = context;
        heroes = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return heroes.size();
    }

    @Override
    public Object getItem(int i) {
        return heroes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            //Menghubungkan ViewHolder dengan View
            view = LayoutInflater.from(context).inflate(R.layout.item_hero, viewGroup, false);
        }

        ViewHolder viewHolder = new ViewHolder(view);

        //Mengubah nilai pahlawan sesuai dari posisinya
        Hero hero = (Hero) getItem(i);
        viewHolder.bind(hero);
        return view;
    }

    private class ViewHolder {
        private TextView txtName;
        private TextView txtDescription;
        private ImageView imgPhoto;

        ViewHolder(View view) {
            txtName = view.findViewById(R.id.txt_name);
            txtDescription = view.findViewById(R.id.txt_description);
            imgPhoto = view.findViewById(R.id.img_photo);
        }

        void bind(Hero hero) {
            txtName.setText(hero.getName());
            txtDescription.setText(hero.getDescription());
            imgPhoto.setImageResource(hero.getPhoto());
        }
    }
}
