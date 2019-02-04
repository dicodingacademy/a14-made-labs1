package com.dicoding.picodiploma.mylistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

class HeroAdapter extends BaseAdapter {

    private final Context context;
    private ArrayList<Hero> heroes;

    public void setHeroes(ArrayList<Hero> heroes) {
        this.heroes = heroes;
    }

    public HeroAdapter(Context context) {
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
            view = LayoutInflater.from(context).inflate(R.layout.item_hero, viewGroup, false);
        }
        Hero hero = (Hero) getItem(i);
        TextView txtName = view.findViewById(R.id.txt_name);
        TextView txtDescription = view.findViewById(R.id.txt_description);
        ImageView imgPhoto = view.findViewById(R.id.img_photo);
        txtName.setText(hero.getName());
        txtDescription.setText(hero.getDescription());
        imgPhoto.setImageResource(hero.getPhoto());
        return view;
    }
}
