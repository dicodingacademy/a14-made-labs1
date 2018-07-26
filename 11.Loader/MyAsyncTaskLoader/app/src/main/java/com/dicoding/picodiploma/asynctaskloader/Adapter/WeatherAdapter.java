package com.dicoding.picodiploma.asynctaskloader.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dicoding.picodiploma.asynctaskloader.R;
import com.dicoding.picodiploma.asynctaskloader.WeatherItems;

import java.util.ArrayList;

/**
 * Created by Emeth on 10/31/2016.
 */

public class WeatherAdapter extends BaseAdapter {

    private ArrayList<WeatherItems> mData = new ArrayList<>();
    private LayoutInflater mInflater;

    public WeatherAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    /**
     * Gunakan method ini jika semua datanya akan diganti
     *
     * @param items kumpulan data baru yang akan mengganti semua data yang sudah ada
     */
    public void setData(ArrayList<WeatherItems> items) {
        mData.clear();
        mData.addAll(items);
        notifyDataSetChanged();
    }

    /**
     * Gunakan method ini jika ada 1 data yang ditambahkan
     *
     * @param item data baru yang akan ditambahkan
     */
    public void addItem(final WeatherItems item) {
        mData.add(item);
        notifyDataSetChanged();
    }

    public void clearData() {
        mData.clear();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public int getCount() {
        // Pengecekan null, diperlukan agar tidak terjadi force close ketika datanya null
        // return 0 sehingga adapter tidak akan menampilkan apapun
        if (mData == null) return 0;

        // Jika tidak null, maka return banyaknya jumlah data yang ada
        return mData.size();
    }

    @Override
    public WeatherItems getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.weather_items, null);
            holder.textViewNamaKota = (TextView) convertView.findViewById(R.id.textKota);
            holder.textViewTemperature = (TextView) convertView.findViewById(R.id.textTemp);
            holder.textViewDescription = (TextView) convertView.findViewById(R.id.textDesc);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textViewNamaKota.setText(mData.get(position).getName());
        holder.textViewTemperature.setText(mData.get(position).getTemperature());
        holder.textViewDescription.setText(mData.get(position).getDescription());
        return convertView;
    }

    private static class ViewHolder {
        TextView textViewNamaKota;
        TextView textViewTemperature;
        TextView textViewDescription;
    }

}





