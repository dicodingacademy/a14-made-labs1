package com.dicoding.www.asynctaskloader;



import android.os.Bundle;
import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.dicoding.www.asynctaskloader.Adapter.WeatherAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<WeatherItems>>  {


    ListView listView ;
    WeatherAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new WeatherAdapter(this);
        adapter.notifyDataSetChanged();
        listView = (ListView)findViewById(R.id.listView);

        listView.setAdapter(adapter);

        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public Loader<ArrayList<WeatherItems>> onCreateLoader(int id, Bundle args) {
        Log.d("Create loader","1");
        return new MyAsyncTaskLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<WeatherItems>> loader, ArrayList<WeatherItems> data) {

        Log.d("Load Finish","1");

        adapter.setData(data);

    }

    @Override
    public void onLoaderReset(Loader<ArrayList<WeatherItems>> loader) {
        Log.d("Load Reset","1");
        adapter.setData(null);

    }

}
