package com.dicoding.picodiploma.mylistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PahlawanAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Pahlawan> pahlawans;

    public void setPahlawans(ArrayList<Pahlawan> pahlawans) {
        this.pahlawans = pahlawans;
    }

    public PahlawanAdapter(Context context, ArrayList<Pahlawan> pahlawans) {
        this.context = context;
        this.pahlawans = pahlawans;
    }

    @Override
    public int getCount() {
        return pahlawans.size();
    }

    @Override
    public Object getItem(int i) {
        return pahlawans.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            view = LayoutInflater.from(context).
                    inflate(R.layout.item_pahlawan, viewGroup, false);
        }

        Pahlawan pahlawan = (Pahlawan)getItem(i);
        TextView txt_nama = view.findViewById(R.id.txt_nama);
        TextView txt_deskripsi = view.findViewById(R.id.txt_deskripsi);
        ImageView img_pahlawan = view.findViewById(R.id.img_president);
        txt_nama.setText(pahlawan.getNama());
        txt_deskripsi.setText(pahlawan.getDeskripsi());
        img_pahlawan.setImageResource(pahlawan.getImage());

        return view;
    }
}
