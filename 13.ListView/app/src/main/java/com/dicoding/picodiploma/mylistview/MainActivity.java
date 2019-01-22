package com.dicoding.picodiploma.mylistview;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String[]data_nama;
    String[]data_deskripsi;
    TypedArray image_pahlawan;
    PahlawanAdapter adapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.lv_list);
        data_nama = getResources().getStringArray(R.array.data_nama);
        data_deskripsi = getResources().getStringArray(R.array.data_deskripsi);
        image_pahlawan = getResources().obtainTypedArray(R.array.data_foto);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, data_nama[i], Toast.LENGTH_SHORT).show();
            }
        });

        addItem();
    }

    public void addItem(){
        ArrayList<Pahlawan> pahlawan = new ArrayList<>();
        adapter = new PahlawanAdapter(this,pahlawan);


        for (int i=0;i<data_nama.length;i++){
            Pahlawan pahlawans = new Pahlawan();
            pahlawans.setImage(image_pahlawan.getResourceId(i,-1));
            pahlawans.setNama(data_nama[i]);
            pahlawans.setDeskripsi(data_deskripsi[i]);
            pahlawan.add(pahlawans);
            adapter.setPahlawans(pahlawan);
        }
        listView.setAdapter(adapter);
    }
}
