package com.dicoding.www.asynctaskloader;

import android.os.Bundle;
import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.dicoding.www.asynctaskloader.Adapter.WeatherAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<WeatherItems>>  {

    ListView listView ;
    WeatherAdapter adapter;
    EditText editKota;
    Button buttonCari;

    static final String EXTRAS_CITY = "EXTRAS_CITY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new WeatherAdapter(this);
        adapter.notifyDataSetChanged();
        listView = (ListView)findViewById(R.id.listView);

        listView.setAdapter(adapter);

        editKota = (EditText)findViewById(R.id.edit_kota);
        buttonCari = (Button)findViewById(R.id.btn_kota);

        buttonCari.setOnClickListener(myListener);

        String kota = editKota.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRAS_CITY,kota);

        //Inisiasi dari Loader, dimasukkan ke dalam onCreate
        getLoaderManager().initLoader(0, bundle, this);
    }

    //Fungsi ini yang akan menjalankan proses myasynctaskloader
    @Override
    public Loader<ArrayList<WeatherItems>> onCreateLoader(int id, Bundle args) {

        String kumpulanKota = "";
        if (args != null){
            kumpulanKota = args.getString(EXTRAS_CITY);
        }

        return new MyAsyncTaskLoader(this,kumpulanKota);
    }

    //Fungsi ini dipanggil ketika proses load sudah selesai
    @Override
    public void onLoadFinished(Loader<ArrayList<WeatherItems>> loader, ArrayList<WeatherItems> data) {

        adapter.setData(data);
    }


    //Fungsi ini dipanggil ketika loader direset
    @Override
    public void onLoaderReset(Loader<ArrayList<WeatherItems>> loader) {
        adapter.setData(null);

    }

    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String kota = editKota.getText().toString();

            // Jika edit text-nya kosong maka do nothing
            if (TextUtils.isEmpty(kota))return;

            Bundle bundle = new Bundle();
            bundle.putString(EXTRAS_CITY,kota);
            getLoaderManager().restartLoader(0,bundle,MainActivity.this);
        }
    };
}