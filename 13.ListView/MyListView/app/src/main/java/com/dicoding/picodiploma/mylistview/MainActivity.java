package com.dicoding.picodiploma.mylistview;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String[] dataName;
    String[] dataDescription;
    TypedArray imagePahlawan;
    PahlawanAdapter adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.lv_list);

        prepare();

        addItem();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, dataName[i], Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void prepare() {
        dataName = getResources().getStringArray(R.array.data_name);
        dataDescription = getResources().getStringArray(R.array.data_description);
        imagePahlawan = getResources().obtainTypedArray(R.array.data_photo);
    }

    private void addItem() {
        ArrayList<Pahlawan> pahlawan = new ArrayList<>();
        adapter = new PahlawanAdapter(this, pahlawan);

        for (int i = 0; i < dataName.length; i++) {
            Pahlawan pahlawans = new Pahlawan();
            pahlawans.setImage(imagePahlawan.getResourceId(i, -1));
            pahlawans.setName(dataName[i]);
            pahlawans.setDescription(dataDescription[i]);
            pahlawan.add(pahlawans);
            adapter.setPahlawans(pahlawan);
        }
        listView.setAdapter(adapter);
    }
}
